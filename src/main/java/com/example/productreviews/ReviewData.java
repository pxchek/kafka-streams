package com.example.productreviews;

import java.util.List;

public class ReviewData {
    public String reviewerID;
    public String asin;
    public String reviewerName;
    public List<Integer> helpful;
    public String reviewText;
    public double overall;
    public String summary;
    public int unixReviewTime;
    public String reviewTime;

    public String getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(String reviewerID) {
        this.reviewerID = reviewerID;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public List<Integer> getHelpful() {
        return helpful;
    }

    public void setHelpful(List<Integer> helpful) {
        this.helpful = helpful;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public double getOverall() {
        return overall;
    }

    public void setOverall(double overall) {
        this.overall = overall;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getUnixReviewTime() {
        return unixReviewTime;
    }

    public void setUnixReviewTime(int unixReviewTime) {
        this.unixReviewTime = unixReviewTime;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }
}
