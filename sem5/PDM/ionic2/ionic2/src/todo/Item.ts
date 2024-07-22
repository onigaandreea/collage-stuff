export interface Song {
    _id?: string;
    artist?: string;
    duration: number;
    title: string;
    dateOfRelease?: Date;
    isFavorite?: boolean;
    isNotSaved?: boolean;
}