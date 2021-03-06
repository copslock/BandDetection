package nu.shel.banddetection;

import java.util.HashMap;

/**
 * LTEBand
 * Created by Seth Shelnutt on 3/30/16.
 * This work was inspired by https://github.com/richliu/earfcn2freq
 * @author Seth Shelnutt
 * @since 3/30/2016
 * This file is copyrighted By Seth Shelnutt and licensed under terms of the LGPL v2.
 */
public class LTEBand {
    public int band;
    private double upload_frequency_lower_bound, upload_frequency_upper_bound, download_frequency_lower_bound, download_frequency_upper_bound;
    private double upload_frequency_offset, download_frequency_offset;

    /** Constructor to create LTEBand object
     *
     * @param band Int for Band
     * @param upload_frequency_lower_bound Frequency upload lower bound
     * @param upload_frequency_upper_bound Frequency upload upper bound
     * @param download_frequency_lower_bound Frequency download lower bound
     * @param download_frequency_upper_bound Frequency download upper bound
     * @param upload_frequency_offset Frequency upload offset
     * @param download_frequency_offset Frequency download offset
     */
    LTEBand(int band, double upload_frequency_lower_bound, double upload_frequency_upper_bound, double download_frequency_lower_bound, double download_frequency_upper_bound, double upload_frequency_offset, double download_frequency_offset) {
        this.band = band;
        this.upload_frequency_lower_bound = upload_frequency_lower_bound;
        this.upload_frequency_upper_bound = upload_frequency_upper_bound;
        this.download_frequency_lower_bound = download_frequency_lower_bound;
        this.download_frequency_upper_bound = download_frequency_upper_bound;
        this.upload_frequency_offset = upload_frequency_offset;
        this.download_frequency_offset = download_frequency_offset;
    }

    /** Checks whether a upload frequency is contained in band object
     *
     * @param earfcn Frequency to check
     * @return True if the upload frequency is contained in this band, else false
     */
    public boolean containsULEarfcn (double earfcn) {
        return earfcn > this.upload_frequency_offset && earfcn < (this.upload_frequency_offset + (this.upload_frequency_upper_bound - this.upload_frequency_lower_bound) * 10);
    }

    /** Checks whether a download frequency is contained in band object
     *
     * @param earfcn Frequency to check
     * @return True if the download frequency is contained in this band, else false
     */
    public boolean containsDLEarfcn (double earfcn) {
        return earfcn > this.download_frequency_offset && earfcn < (this.download_frequency_offset + (this.download_frequency_upper_bound - this.download_frequency_lower_bound) * 10);
    }

    /**
     *
     * @param earfcn Frequency to check
     * @return LTEBand object for matched band, or band 0 object if invalid earfcn is passed
     */
    public static LTEBand GetBandFromEarfcn(double earfcn){
        if(earfcn > 0  && earfcn < 18000) { //DL
            for (LTEBand band: bands.values()) { //Loop through all bands
                if(band.containsDLEarfcn(earfcn)) //If the band contains the earfcn then return it
                    return band;
            }
        } else if (earfcn >=18000 && earfcn <= 65535){ //UL
            for (LTEBand band: bands.values()) { //Loop through all bands
                if(band.containsULEarfcn(earfcn)) //If the band contains the earfcn then return it
                    return band;
            }
        }
        //Invalid input
        return new LTEBand(0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Static list of all LTE Bands
     */
    public static HashMap<Integer, LTEBand> bands = new HashMap<Integer, LTEBand>() {{
                put(1, new LTEBand(1, 1920, 1980, 2110, 2170, 18000, 0));
                put(2, new LTEBand(2, 1850, 1910, 1930, 1990, 18600, 600));
                put(3, new LTEBand(3, 1710, 1785, 1805, 1880, 19200, 1200));
                put(4, new LTEBand(4, 1710, 1755, 2110, 2155, 19950, 1950));
                put(5, new LTEBand(5, 824, 849, 869, 894, 20400, 2400));
                put(6, new LTEBand(6, 830, 840, 875, 885, 20650, 2650));
                put(7, new LTEBand(7, 2500, 2570, 2620, 2690, 20750, 2750));
                put(8, new LTEBand(8, 880, 915, 925, 960, 21450, 3450));
                put(9, new LTEBand(9, 1749.9, 1784.9, 1844.9, 1879.9, 21800, 3800));
                put(10, new LTEBand(10, 1710, 1770, 2110, 2170, 22150, 4150));
                put(11, new LTEBand(11, 1427.0, 1447.9, 1475.9, 1495.9, 22750, 4750));
                put(12, new LTEBand(12, 699, 716, 729, 746, 23010, 5010));
                put(13, new LTEBand(13, 777, 787, 746, 756, 23180, 5180));
                put(14, new LTEBand(14, 788, 798, 758, 768, 23280, 5280));
                put(17, new LTEBand(17, 704, 716, 734, 746, 23730, 5730));
                put(18, new LTEBand(18, 815, 830, 860, 875, 23850, 5850));
                put(19, new LTEBand(19, 830, 845, 875, 890, 24000, 6000));
                put(20, new LTEBand(20, 832, 862, 791, 821, 24150, 6150));
                put(21, new LTEBand(21, 1447.9, 1462.9, 1495.9, 1510.9, 24450, 6450));
                put(22, new LTEBand(22, 3410, 3490, 3510, 3590, 24600, 6600));
                put(23, new LTEBand(23, 2000, 2020, 2180, 2200, 25500, 7500));
                put(24, new LTEBand(24, 1626.5, 1660.5, 1525, 1559, 25700, 7700));
                put(25, new LTEBand(25, 1850, 1915, 1930, 1995, 26040, 8040));
                put(26, new LTEBand(26, 814, 849, 859, 894, 26690, 8690));
                put(27, new LTEBand(27, 807, 824, 852, 869, 27040, 9040));
                put(28, new LTEBand(28, 703, 748, 758, 803, 27210, 9210));
                put(29, new LTEBand(29, 0, 0, 717, 728, 0, 9660));
                put(30, new LTEBand(30, 2305, 2315, 2350, 2360, 27660, 9770));
                put(31, new LTEBand(31, 452.5, 457.5, 462.5, 467.5, 27760, 9870));
                put(33, new LTEBand(33, 1900, 1920, 1900, 1920, 36000, 36000));
                put(34, new LTEBand(34, 2010, 2025, 2010, 2025, 36200, 36200));
                put(35, new LTEBand(35, 1850, 1910, 1850, 1910, 36350, 36350));
                put(36, new LTEBand(36, 1930, 1990, 1930, 1990, 36950, 36950));
                put(37, new LTEBand(37, 1910, 1930, 1910, 1930, 37550, 37550));
                put(38, new LTEBand(38, 2570, 2620, 2570, 2620, 37750, 37750));
                put(39, new LTEBand(39, 1880, 1920, 1880, 1920, 38250, 38250));
                put(40, new LTEBand(40, 2300, 2400, 2300, 2400, 38650, 38650));
                put(41, new LTEBand(41, 2496, 2690, 2496, 2690, 39650, 39650));
                put(42, new LTEBand(42, 3400, 2600, 2400, 3600, 41590, 41590));
                put(43, new LTEBand(43, 3600, 3800, 3600, 3800, 43590, 43590));
                put(44, new LTEBand(44, 703, 803, 703, 803, 45590, 45590));
                put(0, new LTEBand(0, 0, 0, 0, 0, 0, 0));
    }};
}


