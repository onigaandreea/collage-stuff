from domain.validatori import MovieValidator, ClientValidator
from repository.movie_repo import InMemoryRepository_movie
from repository.client_repo import InMemoryRepository_client
from repository.inchirieri_repo import InchirieriInMemoryRepository
from service.movie_service import MovieService
from service.client_service import ClientService
from service.inchirieri_service import InchireriService
from ui.console import Console

val_m = MovieValidator()
val_c = ClientValidator()
repo_m = InMemoryRepository_movie()
repo_c = InMemoryRepository_client()
repo_i = InchirieriInMemoryRepository()
srv_m = MovieService(repo_m, val_m)
srv_c = ClientService(repo_c, val_c)
srv_i = InchireriService(repo_i, repo_m, repo_c)
ui = Console(srv_m, srv_c, srv_i)
ui.show_ui()
