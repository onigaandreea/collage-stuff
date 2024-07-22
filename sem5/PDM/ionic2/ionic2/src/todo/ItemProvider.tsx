import React, { useCallback, useEffect, useReducer, useContext } from 'react';
import PropTypes from 'prop-types';
import { getLogger } from '../core';
import { getAllSongs, updateSongAPI, createSongAPI, newWebSocket, deleteSongAPI } from './ItemApi';
import { Song } from './Item';
import { AuthContext } from '../auth';
import { useNetwork } from '../pages/useNetwork';
import {useIonToast} from "@ionic/react";
import { Preferences } from '@capacitor/preferences';

const log = getLogger('SongProvider');

type UpdateSongFn = (song: Song) => Promise<any>;
type DeleteSongFn = (id: string) => Promise<any>;

interface SongsState {
    songs?: Song[];
    fetching: boolean;
    fetchingError?: Error | null;
    updating: boolean,
    updateError?: Error | null,
    updateSong?: UpdateSongFn,
    addSong?: UpdateSongFn,
    deleteSong?: DeleteSongFn;
    successMessage?: string;
    closeShowSuccess?: () => void;
}

interface ActionProps {
    type: string,
    payload?: any,
}

const initialState: SongsState = {
    fetching: false,
    updating: false,
};

const FETCH_SONGS_STARTED = 'FETCH_SONGS_STARTED';
const FETCH_SONGS_SUCCEEDED = 'FETCH_SONGS_SUCCEEDED';
const FETCH_SONGS_FAILED = 'FETCH_SONGS_FAILED';
const UPDATE_SONG_STARTED = 'UPDATE_SONG_STARTED';
const UPDATE_SONG_SUCCEDED = 'UPDATE_SONG_SUCCEDED';
const UPDATE_SONG_FAILED = 'UPDATE_SONG_FAILED';
const SHOW_SUCCESS_MESSSAGE = 'SHOW_SUCCESS_MESSAGE';
const HIDE_SUCCESS_MESSSAGE = 'HIDE_SUCCESS_MESSAGE';
const CREATE_SONG_STARTED = 'CREATE_SONG_STARTED';
const CREATE_SONG_SUCCEDED = 'CREATE_SONG_SUCCEDED';
const CREATE_SONG_FAILED = 'CREATE_SONG_FAILED';
const DELETE_SONG_STARTED = 'DELETE_SONG_STARTED';
const DELETE_SONG_SUCCEDED = 'DELETE_SONG_SUCCEDED';
const DELETE_SONG_FAILED = 'DELETE_SONG_FAILED';

const reducer: (state: SongsState, action: ActionProps) => SongsState 
    = (state, { type, payload }) => {
    switch(type){
        case FETCH_SONGS_STARTED:
            return { ...state, fetching: true, fetchingError: null };
        case FETCH_SONGS_SUCCEEDED:
            return {...state, songs: payload.songs, fetching: false };
        case FETCH_SONGS_FAILED:
            return { ...state, fetchingError: payload.error, fetching: false };
        case UPDATE_SONG_STARTED:
            return { ...state, updateError: null, updating: true };
        case UPDATE_SONG_FAILED:
            return { ...state, updateError: payload.error, updating: false };
        case UPDATE_SONG_SUCCEDED:
            const songs = [...(state.songs || [])];
            const song = payload.song;
            const index = songs.findIndex(it => it._id === song._id);
            songs[index] = song;
            return { ...state,  songs, updating: false };
        case CREATE_SONG_FAILED:
            console.log(payload.error);
          return { ...state, updateError: payload.error, updating: false };
        case CREATE_SONG_STARTED:
          return { ...state, updateError: null, updating: true };
        case CREATE_SONG_SUCCEDED:
            const beforeSongs = [...(state.songs || [])];
            const createdSong = payload.song;
            console.log(createdSong);
            const indexOfAdded = beforeSongs.findIndex(it => it._id === createdSong._id || it.title === createdSong.title);
            console.log("index: ", indexOfAdded);
            if (indexOfAdded === -1) {
                beforeSongs.splice(0, 0, createdSong);
            } else {
                beforeSongs[indexOfAdded] = createdSong;
            }
            console.log(beforeSongs);
            console.log(payload);
            return { ...state,  songs: beforeSongs, updating: false, updateError: null };
            case DELETE_SONG_FAILED:
              console.log(payload.error);
              return { ...state, updateError: payload.error, updating: false };
            case DELETE_SONG_STARTED:
              return { ...state, updateError: null, updating: true };
            case DELETE_SONG_SUCCEDED:
                const originalSongs = [...(state.songs || [])];
                const deletedSong = payload.song;
                const indexOfDeleted = originalSongs.findIndex(it => it._id === deletedSong._id);
                if (indexOfDeleted > -1) {
                  originalSongs.splice(indexOfDeleted, 1);
                }
                console.log(originalSongs);
                console.log(payload);
                return { ...state,  songs: originalSongs, updating: false };
        case SHOW_SUCCESS_MESSSAGE:
            const allSongs = [...(state.songs || [])];
            const updatedSong = payload.updatedSong;
            const indexOfSong = allSongs.findIndex(it => it._id === updatedSong._id);
            allSongs[indexOfSong] = updatedSong;
            console.log(payload);
            return {...state, songs: allSongs, successMessage: payload.successMessage }
        case HIDE_SUCCESS_MESSSAGE:
            return {...state, successMessage: payload }
        
        default:
            return state;
    }
};

export const SongsContext = React.createContext(initialState);

interface SongProviderProps {
    children: PropTypes.ReactNodeLike,
}

export const SongProvider: React.FC<SongProviderProps> = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    const { songs, fetching, fetchingError, updating, updateError, successMessage } = state;
    const { token } = useContext(AuthContext);
    const { networkStatus } = useNetwork();
    const [toast] = useIonToast();

    useEffect(getItemsEffect, [token]);
    useEffect(wsEffect, [token]);
    useEffect(executePendingOperations, [networkStatus.connected, token, toast]);

    const updateSong = useCallback<UpdateSongFn>(updateSongCallback, [token]);
    const addSong = useCallback<UpdateSongFn>(addSongCallback, [token]);
    const deleteSong = useCallback<DeleteSongFn>(deleteSongCallback, [token]);

    log('returns');

    function getItemsEffect() {
        let canceled = false;
        fetchItems();
        return () => {
            canceled = true;
        }

        async function fetchItems() {
          if(!token?.trim()){
            return;
          }

            try{
                log('fetchBooks started');
                dispatch({ type: FETCH_SONGS_STARTED });
                const songs = await getAllSongs(token);
                log('fetchItems succeeded');
                if (!canceled) {
                dispatch({ type: FETCH_SONGS_SUCCEEDED, payload: { songs } });
                }
            } catch (error) {
                log('fetchItems failed');
                if (!canceled) {
                    dispatch({ type: FETCH_SONGS_FAILED, payload: { error } });
                }
            }
        }
    }

    async function updateSongCallback(song: Song) {
        try {
          log('updateSong started');
          dispatch({ type: UPDATE_SONG_STARTED });
          const updatedSong = await updateSongAPI(token, song);
          log('saveSong succeeded');
          dispatch({ type: UPDATE_SONG_SUCCEDED, payload: { song: updatedSong } });
        } catch (error: any) {
          log('updateSong failed');
          // save item to storage
          console.log('Updating song locally...');

          song.isNotSaved = true;
          await Preferences.set({
            key: `upd-${song.title}`,
            value: JSON.stringify({token, song })
          });
          dispatch({ type: UPDATE_SONG_SUCCEDED, payload: { song: song } });
          toast("You are offline... Updating song locally!", 3000);
    
          if(error.toJSON().message === 'Network Error')
            dispatch({ type: UPDATE_SONG_FAILED, payload: { error: new Error(error.response) } });
        }
    }

    async function addSongCallback(song: Song){
        try{
          log('addSong started');
          dispatch({ type: CREATE_SONG_STARTED });
          console.log(token);
          const addedSong = await createSongAPI(token, song);
          console.log(addedSong);
          log('saveSong succeeded');
          dispatch({ type: CREATE_SONG_SUCCEDED, payload: { song: addedSong } });
        }catch(error: any){
          log('addSong failed');
          console.log(error.response);
          // save item to storage
          console.log('Saving song locally...');
          const { keys } = await Preferences.keys();
          const matchingKeys = keys.filter(key => key.startsWith('sav-'));
          const numberOfItems = matchingKeys.length + 1;
          console.log(numberOfItems);

          song._id = numberOfItems.toString(); // ii adaug si id...
          song.isNotSaved = true;
          await Preferences.set({
            key: `sav-${song.title}`,
            value: JSON.stringify({token, song })
          });
          dispatch({ type: CREATE_SONG_SUCCEDED, payload: { song: song } });
          toast("You are offline... Saving song locally!", 3000);
    
          if(error.toJSON().message === 'Network Error')
            dispatch({ type: CREATE_SONG_FAILED, payload: { error: new Error(error.response || 'Network error') } });
        }
    }

    async function deleteSongCallback(id: string){
        try{
          log('deleteSong started');
          dispatch({ type: DELETE_SONG_STARTED });
          const deletedSong = await deleteSongAPI(token, id);
          console.log('deleted song: '+ deletedSong);
          log('deleteSong succeeded');
          dispatch({ type: DELETE_SONG_SUCCEDED, payload: { song: deletedSong } });
        }catch(error: any){
          log('addSong failed');
          console.log(error.response.data.message);
          dispatch({ type: DELETE_SONG_FAILED, payload: { error: new Error(error.response.data.message) } });
        }
    }

    function executePendingOperations(){
      async function helperMethod(){
          if(networkStatus.connected && token?.trim()){
              log('executing pending operations')
              const { keys } = await Preferences.keys();
              for(const key of keys) {
                  if(key.startsWith("sav-")){
                      const res = await Preferences.get({key: key});
                      console.log("Result", res);
                      if (typeof res.value === "string") {
                          const value = JSON.parse(res.value);
                          value.song._id=undefined;  // ca sa imi puna serverul id nou!!
                          log('creating item from pending', value);
                          await addSongCallback(value.song);
                          await Preferences.remove({key: key});
                      }
                  }
              }
              for(const key of keys) {
                if(key.startsWith("upd-")){
                    const res = await Preferences.get({key: key});
                    console.log("Result", res);
                    if (typeof res.value === "string") {
                        const value = JSON.parse(res.value);
                        log('updating item from pending', value);
                        await updateSongCallback(value.song);
                        await Preferences.remove({key: key});
                    }
                }
            }
          }
      }
      helperMethod();
  }

    function wsEffect() {
        let canceled = false;
        log('wsEffect - connecting');
        let closeWebSocket: () => void;
        if(token?.trim()){
          closeWebSocket = newWebSocket(token, message => {
            if (canceled) {
              return;
            }
            const { event, payload } = message;
            console.log('Provider message: ', message);

            log(`ws message, item ${event}`);
            if (event === 'updated') {
              console.log(payload);
              dispatch({ type: SHOW_SUCCESS_MESSSAGE, payload: {successMessage: payload.successMessage, updatedSong: payload.updatedSong } });
            }
            else if(event == 'created'){
              console.log(payload);
              dispatch({ type: CREATE_SONG_SUCCEDED, payload: { song: payload.updatedSong } });
            }
            else if(event === 'deleted'){
              console.log(payload);
              dispatch({ type: DELETE_SONG_SUCCEDED, payload: { song: payload.updatedSong } });
            }
          });
        }
        return () => {
          log('wsEffect - disconnecting');
          canceled = true;
          closeWebSocket?.();
        }
    }

    function closeShowSuccess(){
        dispatch({ type: HIDE_SUCCESS_MESSSAGE, payload: null });
    }

    const value = { songs, fetching, fetchingError, updating, updateError, updateSong, addSong, deleteSong, successMessage, closeShowSuccess };

    return (
        <SongsContext.Provider value={value}>
            {children}
        </SongsContext.Provider>
    );
};

