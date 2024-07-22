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
  IonLabel,
  IonDatetime,
  IonSelect,
  IonSelectOption
} from '@ionic/react';
import { getLogger } from '../core';
import { RouteComponentProps } from 'react-router';
import { SongsContext } from './ItemProvider';
import { Song } from './Item';

const log = getLogger('SaveLogger');

interface SongEditProps extends RouteComponentProps<{
  id?: string;
}> {}

export const SongAdd: React.FC<SongEditProps> = ({ history, match }) => {
  const { songs, updating, updateError, addSong } = useContext(SongsContext);
  const [title, setTitle] = useState('');
  const [duration, setDuration] = useState('');
  const [artist, setArtist] = useState('');
  const [date, setDate] = useState(new Date());
  const [option, setOption] = useState(true);
  const [songToUpdate, setSongToUpdate] = useState<Song>();

  const handleAdd = useCallback(() => {
    const editedSong ={ ...songToUpdate, title: title, artist: artist, duration: parseFloat(duration), dateOfRelease: date, isFavorite: option };
    //console.log(duration);
    //console.log(editedSong);
    log(editedSong);
    console.log(updateError);
    addSong && addSong(editedSong).then(() => editedSong.duration && history.goBack());
  }, [songToUpdate, addSong, title, duration, date, artist, option, history]);

  const dateChanged = (value: any) => {
    let formattedDate = value;
    console.log(formattedDate);
    setDate(formattedDate);
  };

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
        <IonButtons slot="start">
            <IonBackButton></IonBackButton>
          </IonButtons>
          <IonTitle>Edit</IonTitle>
          <IonButtons slot="end">
            <IonButton onClick={handleAdd}>
              Add
            </IonButton>
          </IonButtons>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonInput label="Title:" placeholder="Title" value={title} onIonInput={e => setTitle(prev => e.detail.value || '')} />
        <IonInput label="Artist:" placeholder="Artist" value={artist} onIonInput={e => setArtist(prev => e.detail.value || '')} />
        <IonInput label="Duration:" placeholder="Duration" value={duration} onIonInput={e => e.detail.value ? setDuration(prev => e.detail.value!) : setDuration('') }/>
        <IonInput label="DateOfRelease:" placeholder="Choose date" value={new Date(date).toDateString()} />
        <IonDatetime
                onIonChange={(e) => dateChanged(e.detail.value)}>
        </IonDatetime>
        <IonInput label="Favorite:" placeholder="True/False" value={option==true ? 'True' : 'False'} />
        <IonSelect value={option} onIonChange={e => setOption(e.detail.value)}>
          <IonSelectOption value={true}>
            {'True'}
          </IonSelectOption>
          <IonSelectOption value={false}>
            {'False'}
          </IonSelectOption>
        </IonSelect>
        <IonLoading isOpen={updating} />
        {updateError && (
          <div>{updateError.message || 'Failed to save item'}</div>
        )}
      </IonContent>
    </IonPage>
  );
}
