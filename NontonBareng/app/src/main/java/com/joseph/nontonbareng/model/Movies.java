package com.joseph.nontonbareng.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {

    String imgPoster;
    String title;
    String releaseDate;
    String rating;

    String imgDetailPoster;
    String imgDetailBackdrop;
    String ratingViewer;
    String viewer;
    String overview;

    public Movies(
            String imgPoster,String title, String releaseDate, String rating,
            String imgDetailPoster, String imgDetailBackdrop, String ratingViewer, String viewer, String overview) {

        this.imgPoster = imgPoster;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;

        this.imgDetailPoster = imgDetailPoster;
        this.imgDetailBackdrop = imgDetailBackdrop;
        this.ratingViewer = ratingViewer;
        this.viewer = viewer;
        this.overview = overview;
    }

    public Movies() { }

    public String getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(String imgPoster) {
        this.imgPoster = imgPoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingViewer() {
        return ratingViewer;
    }

    public void setRatingViewer(String ratingViewer) {
        this.ratingViewer = ratingViewer;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImgDetailPoster() {
        return imgDetailPoster;
    }

    public void setImgDetailPoster(String imgDetailPoster) { this.imgDetailPoster = imgDetailPoster; }

    public String getImgDetailBackdrop() {
        return imgDetailBackdrop;
    }

    public void setImgDetailBackdrop(String imgDetailBackdrop) { this.imgDetailBackdrop = imgDetailBackdrop; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(this.imgPoster);
        parcel.writeString(this.title);
        parcel.writeString(this.releaseDate);
        parcel.writeString(this.rating);

        parcel.writeString(this.imgDetailPoster);
        parcel.writeString(this.imgDetailBackdrop);
        parcel.writeString(this.ratingViewer);
        parcel.writeString(this.viewer);
        parcel.writeString(this.overview);
    }

    public void readFromParcel(Parcel source) {

        this.imgPoster = source.readString();
        this.title = source.readString();
        this.releaseDate = source.readString();
        this.rating = source.readString();

        this.imgDetailPoster = source.readString();
        this.imgDetailBackdrop = source.readString();
        this.ratingViewer = source.readString();
        this.viewer = source.readString();
        this.overview = source.readString();
    }

    protected Movies(Parcel in) {

        this.imgPoster = in.readString();
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.rating = in.readString();

        this.imgDetailPoster = in.readString();
        this.imgDetailBackdrop = in.readString();
        this.ratingViewer = in.readString();
        this.viewer = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) { return new Movies[size]; }
    };
}
