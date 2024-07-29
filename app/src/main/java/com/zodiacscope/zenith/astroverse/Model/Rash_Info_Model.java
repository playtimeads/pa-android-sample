package com.zodiacscope.zenith.astroverse.Model;

public class Rash_Info_Model {
    private String nameEnglish;
    private String nameGujarati;
    private String nameHindi;
    private int imageResourceId;
    private String sr_gujnamakhsar;
    private String sr_hindinamakshar;
    private String sr_englishnamakhsar;
    private String longitude;

    public Rash_Info_Model(String nameEnglish, String nameGujarati, String nameHindi, int imageResourceId, String sr_guj_namakhsar, String sr_hin_namakhsar, String sr_eng_namakhsar, String longitude) {
        this.nameEnglish = nameEnglish;
        this.nameGujarati = nameGujarati;
        this.nameHindi = nameHindi;
        this.imageResourceId = imageResourceId;
        this.sr_gujnamakhsar = sr_guj_namakhsar;
        this.sr_hindinamakshar = sr_hin_namakhsar;
        this.sr_englishnamakhsar = sr_eng_namakhsar;
        this.longitude = longitude;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getNameGujarati() {
        return nameGujarati;
    }

    public String getNameHindi() {
        return nameHindi;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String get_gujnamakshar() {
        return sr_gujnamakhsar;
    }


    public String getSr_gujnamakhsar() {
        return sr_gujnamakhsar;
    }

    public String getSr_hindinamakshar() {
        return sr_hindinamakshar;
    }


    public String getSr_englishnamakhsar() {
        return sr_englishnamakhsar;
    }

    public String getLongitude() {
        return longitude;
    }
}
