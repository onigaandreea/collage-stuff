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
  IonItem,
  IonImg,
  IonFab,
  IonFabButton,
  IonIcon
} from '@ionic/react';
import { getLogger } from '../core';
import { RouteComponentProps } from 'react-router';
import { SongsContext } from './ItemProvider';
import { Song } from './Item';
import { camera } from 'ionicons/icons';
import { usePhotos } from '../pages/usePhotos';
import { useMyLocation } from '../pages/useMyLocation';
import MyMap from '../components/MyMap';
import './image.css'

const log = getLogger('EditLogger');

interface SongEditProps extends RouteComponentProps<{
  id?: string;
}> {}

export const SongEdit: React.FC<SongEditProps> = ({ history, match }) => {
  const { songs, updating, updateError, updateSong, deleteSong } = useContext(SongsContext);
  const [title, setTitle] = useState('');
  const [duration, setDuration] = useState('');
  const [songToUpdate, setSongToUpdate] = useState<Song>();

  const [photo, setPhoto] = useState<string|undefined>('');

  const [latitude, setLatitude] = useState<number|undefined>(0);
  const [longitude, setLongitude] = useState<number|undefined>(0);

  const myLocation = useMyLocation();
  const { latitude: lat, longitude: lng } = myLocation.position?.coords || {}

  const [showModal, setShowModal] = useState<boolean>(false);

  useEffect(() => {
    const routeId = match.params.id || '';
    console.log(routeId);
    //const idNumber = parseInt(routeId);
    const song = songs?.find(it => it._id === routeId);
    setSongToUpdate(song);
    if(song){
      setTitle(song.title);
      setDuration(song.duration.toString());
      setPhoto(song.photo);
      setLatitude(song.latitude ? song.latitude : 36);
      setLongitude(song.longitude ? song.longitude : 40);
    }
    else{
      setLatitude(lat);
      setLongitude(lng);
    }
  }, [match.params.id, songs]);

  const handleUpdate = useCallback(() => {
    const editedSong ={ ...songToUpdate, title: title, duration: parseFloat(duration), photo: photo, latitude: latitude, longitude: longitude };
    log(editedSong);
    console.log(updateSong);
    updateSong && updateSong(editedSong).then(() => editedSong.duration && history.goBack());
  }, [songToUpdate, updateSong, title, duration, photo, latitude, longitude, history]);

  const handleDelete = useCallback(()=>{
    console.log(songToUpdate?._id);
    deleteSong && deleteSong(songToUpdate?._id!).then(()=> history.goBack());
  }, [songToUpdate, deleteSong, title, duration, history]);

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
            <IonButton onClick={handleUpdate}>
              Update
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
      <div>My Location is</div>
      <div>latitude: {latitude}</div>
      <div>longitude: {longitude}</div>
      {latitude && longitude &&
            <IonItem>
              <MyMap
                  lat={latitude}
                  lng={longitude}
                  onMapClick={(position)=>{
                    setLatitude(position.latitude);
                    setLongitude(position.longitude);
                  }}
                  onMarkerClick={()=>log('onMarker')}
              />
            </IonItem>
        }
        <IonInput label="Title:" placeholder="New Title" value={title} onIonInput={e => setTitle(prev => e.detail.value || '')} />
        <IonInput label="Duration:" placeholder="New duration" value={duration} onIonInput={e => e.detail.value ? setDuration(prev => e.detail.value!) : setDuration('') }/>
        <IonLoading isOpen={updating} />
        {updateError && (
          <div>{updateError.message || 'Failed to update item'}</div>
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
