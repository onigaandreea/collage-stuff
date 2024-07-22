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
  IonDatetime,
  IonSelect,
  IonSelectOption, IonItem,
  IonImg,
  IonIcon, IonFab, IonFabButton,
} from '@ionic/react';
import { getLogger } from '../core';
import { RouteComponentProps } from 'react-router';
import { SongsContext } from './ItemProvider';
import { Song } from './Item';
import { useMyLocation } from '../pages/useMyLocation';
import { usePhotos } from '../pages/usePhotos';
import {camera} from "ionicons/icons";
import MyMap from '../components/MyMap';

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

  const [photo, setPhoto] = useState<string|undefined>('');

  const [latitude, setLatitude] = useState<number|undefined>(0);
  const [longitude, setLongitude] = useState<number|undefined>(0);

  const myLocation = useMyLocation();
  const { latitude: lat, longitude: lng } = myLocation.position?.coords || {}

  const handleAdd = useCallback(() => {
    const editedSong ={ ...songToUpdate, title: title, artist: artist, duration: parseFloat(duration), dateOfRelease: date, isFavorite: option, photo: photo, latitude: latitude, longitude: longitude };
    log(editedSong);
    console.log(updateError);
    addSong && addSong(editedSong).then(() => editedSong.duration && history.goBack());
  }, [songToUpdate, addSong, title, duration, date, artist, option, photo, latitude, longitude, history]);

  const dateChanged = (value: any) => {
    let formattedDate = value;
    console.log(formattedDate);
    setDate(formattedDate);
  };

  const {photos, takePhoto, deletePhoto,}=usePhotos();
  const [photoTaken, setPhotoTaken]=useState(false);

  useEffect(() => {
    log('useEffect');
    photoTaken && photos && photos[0] && photos[0].webviewPath && setPhoto(photos[0].webviewPath)
  }, [photos,photoTaken]);

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
      {photo && (
        <IonItem>
          <IonImg class="ion-img" src={photo} onClick={() => setPhoto('')} />
        </IonItem>
      )}
      <div>latitudine: {latitude}</div>
      <div>longitudine: {longitude} </div>
      {latitude && longitude &&
            <IonItem>
              <MyMap
                  lat={latitude}
                  lng={longitude}
                  onMapClick={(position)=>{
                    setLatitude(position.latitude);
                    setLongitude(position.longitude);
                    log('onMap')
                  }}
                  onMarkerClick={()=>log('onMarker')}
              />
            </IonItem>
        }
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
        <IonFab vertical="bottom" horizontal="center" slot="fixed">
          <IonFabButton onClick={async () => {
            try {
              await takePhoto();
              setPhotoTaken(true);
            }
            catch (e){

            }
          }}>
            <IonIcon icon={camera}/>
          </IonFabButton>
        </IonFab>
      </IonContent>
    </IonPage>
  );
}
