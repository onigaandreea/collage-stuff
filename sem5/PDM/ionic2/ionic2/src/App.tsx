import { Redirect, Route } from 'react-router-dom';
import { IonApp, IonRouterOutlet, setupIonicReact } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';

/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import './theme/variables.css';

import { SongsList } from './todo/ItemList';
import { SongEdit } from './todo/ItemEdit';
import { SongAdd } from './todo/ItemAdd';
import { SongProvider } from './todo/ItemProvider';
import { AuthProvider, Login, PrivateRoute } from './auth';

setupIonicReact();

const App: React.FC = () => (
    <IonApp>
      <IonReactRouter>
        <IonRouterOutlet>
          <AuthProvider>
            <Route path="/login" component={Login} exact={true}/>
              <SongProvider>
                <PrivateRoute path="/items" component={SongsList} exact={true}/>
                <Route path="/song" component={SongAdd} exact={true}/>
                <Route path="/song/:id" component={SongEdit} exact={true}/>
              </SongProvider>
            <Route exact path="/" render={() => <Redirect to="/items"/>}/>
          </AuthProvider>
        </IonRouterOutlet>
      </IonReactRouter>

    </IonApp>
  
);

export default App;
