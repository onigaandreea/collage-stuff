import React, { useContext, useEffect, useState } from 'react';
import { RouteComponentProps } from 'react-router';
import SongComponent from './Component';
import { getLogger } from '../core';
import { SongsContext } from './ItemProvider';
import { IonContent, 
         IonHeader, 
         IonList, 
         IonLoading, 
         IonPage, 
         IonTitle, 
         IonToolbar,
         IonToast, 
         IonFab,
         IonFabButton,
         IonIcon,
         IonButton, 
         IonButtons,
         IonInfiniteScroll,
         IonInfiniteScrollContent,
         IonSearchbar,
         IonSelect, IonSelectOption } from '@ionic/react';

import { add } from 'ionicons/icons';
import { AuthContext } from '../auth';
import { NetworkState } from '../pages/NetworkState';
import { Song } from './Item';

const log = getLogger('SongsList');
const songsPerPage = 3;
const filterValues = ["isFavorite", "NotFavorite"];

export const SongsList: React.FC<RouteComponentProps> = ({ history }) => {
  const { songs, fetching, fetchingError, successMessage, closeShowSuccess } = useContext(SongsContext);
  const { logout } = useContext(AuthContext);
  const [isOpen, setIsOpen]= useState(false);
  const [index, setIndex] = useState<number>(0);
  const [songsAux, setSongsAux] = useState<Song[] | undefined>([]);
  const [more, setHasMore] = useState(true);
  const [searchText, setSearchText] = useState('');
  const [filter, setFilter] = useState<string | undefined>(undefined);
  //const [hasFetched, setHasFetched] = useState(false);

  useEffect(()=>{
    if(fetching) setIsOpen(true);
    else setIsOpen(false);
  }, [fetching]);

  log('render');
  console.log(songs);

  function handleLogout(){
    logout?.();
    history.push('/login');
  }

  //pagination
  useEffect(()=>{
    fetchData();
  }, [songs]);

  // searching
  useEffect(()=>{
    if (searchText === "") {
      setSongsAux(songs);
    }
    if (songs && searchText !== "") {
      setSongsAux(songs.filter(song => song.artist!.startsWith(searchText)));
    }
  }, [searchText]);

   // filtering
   useEffect(() => {
    if (songs && filter) {
        setSongsAux(songs.filter(song => {
            if (filter === "isFavorite")
                return song.isFavorite === true;
            else
                return song.isFavorite === false;
        }));
    }
}, [filter]);

  function fetchData() {
    if(songs){
      const newIndex = Math.min(index + songsPerPage, songs.length);
      if( newIndex >= songs.length){
          setHasMore(false);
      }
      else{
          setHasMore(true);
      }
      setSongsAux(songs.slice(0, newIndex));
      setIndex(newIndex);
    }
  }

  async function searchNext($event: CustomEvent<void>){
    await fetchData();
    await ($event.target as HTMLIonInfiniteScrollElement).complete();
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Songs App</IonTitle>
          <IonSelect 
            slot="end" 
            value={filter} 
            placeholder="Filter" 
            onIonChange={(e) => setFilter(e.detail.value)}>
                        {filterValues.map((each) => (
                            <IonSelectOption key={each} value={each}>
                                {each}
                            </IonSelectOption>
                        ))}
          </IonSelect>
          <NetworkState />
          <IonSearchbar placeholder="Search by artist" value={searchText} debounce={200} onIonInput={(e) => {
                        setSearchText(e.detail.value!);
                    }} slot="secondary">
          </IonSearchbar>
          <IonButtons slot='end'>
             <IonButton onClick={handleLogout}>Logout</IonButton>
          </IonButtons>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonLoading isOpen={isOpen} message="Fetching songs..." />
        {songsAux && (
          <IonList>
            {songsAux.map(song => 
              <SongComponent key={song._id} _id={song._id} 
              artist={song.artist}
              title={song.title} 
              duration={song.duration} 
              dateOfRelease={song.dateOfRelease}
              isFavorite={song.isFavorite} 
              isNotSaved={song.isNotSaved}
              onEdit={id => history.push(`/song/${id}`)} /> 
            )}
          </IonList>
        )}
        <IonInfiniteScroll threshold="100px" disabled={!more} onIonInfinite={(e:CustomEvent<void>) => searchNext(e)} >
          <IonInfiniteScrollContent loadingText="Loading more food..." >
          </IonInfiniteScrollContent>
        </IonInfiniteScroll>
        {fetchingError && (
          <div>{fetchingError.message || 'Failed to fetch songs'}</div>
        )}
        <IonFab vertical="bottom" horizontal="end" slot="fixed">
          <IonFabButton onClick={() => history.push('/song')}>
            <IonIcon icon={add} />
          </IonFabButton>
        </IonFab>
        <IonToast
          isOpen={!!successMessage}
          message={successMessage}
          position='bottom'
          buttons={[
            {
              text: 'Dismiss',
              role: 'cancel',
              handler: () => {
                console.log('More Info clicked');
              },
            }]}
          onDidDismiss={closeShowSuccess}
          duration={5000}
          />
      </IonContent>
    </IonPage>
  );
};