import React, { useCallback, useContext, useEffect, useState } from 'react';
import {
  IonButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonInput,
  IonLoading,
  IonPage,
  IonTitle,
  IonToolbar,
  IonBackButton,
  IonLabel
} from '@ionic/react';
import { getLogger } from '../core';
import { RouteComponentProps } from 'react-router';
import { SongsContext } from './ItemProvider';
import { Song } from './Item';

const log = getLogger('EditLogger');

interface SongEditProps extends RouteComponentProps<{
  id?: string;
}> {}

export const SongEdit: React.FC<SongEditProps> = ({ history, match }) => {
  const { songs, updating, updateError, updateSong, deleteSong } = useContext(SongsContext);
  const [title, setTitle] = useState('');
  const [duration, setDuration] = useState('');
  const [songToUpdate, setSongToUpdate] = useState<Song>();

  useEffect(() => {
    const routeId = match.params.id || '';
    console.log(routeId);
    //const idNumber = parseInt(routeId);
    const song = songs?.find(it => it._id === routeId);
    setSongToUpdate(song);
    if(song){
      setTitle(song.title);
      setDuration(song.duration.toString());
    }
  }, [match.params.id, songs]);

  const handleUpdate = useCallback(() => {
    const editedSong ={ ...songToUpdate, title: title, duration: parseFloat(duration) };
    log(editedSong);
    console.log(updateSong);
    updateSong && updateSong(editedSong).then(() => editedSong.duration && history.goBack());
  }, [songToUpdate, updateSong, title, duration, history]);

  const handleDelete = useCallback(()=>{
    console.log(songToUpdate?._id);
    deleteSong && deleteSong(songToUpdate?._id!).then(()=> history.goBack());
  }, [songToUpdate, deleteSong, title, duration, history]);

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
        <IonButtons slot="start">
            <IonBackButton></IonBackButton>
          </IonButtons>
          <IonTitle>Edit</IonTitle>
          <IonButtons slot="end">
            <IonButton onClick={handleUpdate}>
              Update
            </IonButton>
          </IonButtons>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonInput label="Title:" placeholder="New Title" value={title} onIonInput={e => setTitle(prev => e.detail.value || '')} />
        <IonInput label="Duration:" placeholder="New duration" value={duration} onIonInput={e => e.detail.value ? setDuration(prev => e.detail.value!) : setDuration('') }/>
        <IonLoading isOpen={updating} />
        {updateError && (
          <div>{updateError.message || 'Failed to update item'}</div>
        )}
      </IonContent>
    </IonPage>
  );
}
