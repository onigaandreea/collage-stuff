export interface Song {
    _id?: string;
    artist?: string;
    duration: number;
    title: string;
    dateOfRelease?: Date;
    isFavorite?: boolean;
    isNotSaved?: boolean;
    photo?: string;
    latitude?: number|undefined
    longitude?: number|undefined
}