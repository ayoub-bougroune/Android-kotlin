package com.example.coronaviruslive.countrylive

import android.os.Parcel
import android.os.Parcelable

class CountryLive : Parcelable {
    var mCovidCountry: String?
    var mTodayCases: String?
    var mDeaths: String?
    var mTodayDeaths: String?
    var mRecovered: String?
    var mActive: String?
    var mCritical: String?
    var mFlags: String?
    var mCases: Int

    constructor(mCovidCountry: String?, mCases: Int, mTodayCases: String?, mDeaths: String?, mTodayDeaths: String?, mRecovered: String?, mActive: String?, mCritical: String?, mFlags: String?) {
        this.mCovidCountry = mCovidCountry
        this.mCases = mCases
        this.mTodayCases = mTodayCases
        this.mDeaths = mDeaths
        this.mTodayDeaths = mTodayDeaths
        this.mRecovered = mRecovered
        this.mActive = mActive
        this.mCritical = mCritical
        this.mFlags = mFlags
    }

    fun getmCovidCountry(): String? {
        return mCovidCountry
    }

    fun getmCases(): Int {
        return mCases
    }

    fun getmTodayCases(): String? {
        return mTodayCases
    }

    fun getmDeaths(): String? {
        return mDeaths
    }

    fun getmTodayDeaths(): String? {
        return mTodayDeaths
    }

    fun getmRecovered(): String? {
        return mRecovered
    }

    fun getmActive(): String? {
        return mActive
    }

    fun getmCritical(): String? {
        return mCritical
    }

    fun getmFlags(): String? {
        return mFlags
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(mCovidCountry)
        dest.writeInt(mCases)
        dest.writeString(mTodayCases)
        dest.writeString(mDeaths)
        dest.writeString(mTodayDeaths)
        dest.writeString(mRecovered)
        dest.writeString(mActive)
        dest.writeString(mCritical)
        dest.writeString(mFlags)
    }

    protected constructor(`in`: Parcel) {
        mCovidCountry = `in`.readString()
        mCases = `in`.readInt()
        mTodayCases = `in`.readString()
        mDeaths = `in`.readString()
        mTodayDeaths = `in`.readString()
        mRecovered = `in`.readString()
        mActive = `in`.readString()
        mCritical = `in`.readString()
        mFlags = `in`.readString()
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CountryLive?> = object : Parcelable.Creator<CountryLive?> {
            override fun createFromParcel(source: Parcel): CountryLive? {
                return CountryLive(source)
            }

            override fun newArray(size: Int): Array<CountryLive?> {
                return arrayOfNulls(size)
            }
        }
    }
}


