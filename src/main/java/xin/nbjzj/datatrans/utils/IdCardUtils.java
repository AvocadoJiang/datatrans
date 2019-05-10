package xin.nbjzj.datatrans.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class IdCardUtils {
	static final Map<Integer, String> zoneNum = new HashMap();
    static final int[] PARITYBIT;
    static final int[] POWER_LIST;

    public IdCardUtils() {
    }

    public static boolean isIDCard(String certNo) {
        if (certNo == null || certNo.length() != 15 && certNo.length() != 18) {
            return false;
        } else {
            char[] cs = certNo.toUpperCase().toCharArray();
            int power = 0;

            for(int i = 0; i < cs.length && (i != cs.length - 1 || cs[i] != 'X'); ++i) {
                if (cs[i] < '0' || cs[i] > '9') {
                    return false;
                }

                if (i < cs.length - 1) {
                    power += (cs[i] - 48) * POWER_LIST[i];
                }
            }

            if (!zoneNum.containsKey(Integer.valueOf(certNo.substring(0, 2)))) {
                return false;
            } else {
                String year = certNo.length() == 15 ? "19" + certNo.substring(6, 8) : certNo.substring(6, 10);
                int iyear = Integer.parseInt(year);
                if (iyear >= 1900 && iyear <= Calendar.getInstance().get(1)) {
                    String month = certNo.length() == 15 ? certNo.substring(8, 10) : certNo.substring(10, 12);
                    int imonth = Integer.parseInt(month);
                    if (imonth >= 1 && imonth <= 12) {
                        String day = certNo.length() == 15 ? certNo.substring(10, 12) : certNo.substring(12, 14);
                        int iday = Integer.parseInt(day);
                        if (iday >= 1 && iday <= 31) {
                            return certNo.length() == 15 || cs[cs.length - 1] == PARITYBIT[power % 11];
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public static String get15Ic(String identityCard) {
        String retId = "";
        if (null == identityCard) {
            return retId;
        } else if (identityCard.length() == 18) {
            retId = identityCard.substring(0, 6) + identityCard.substring(8, 17);
            return retId;
        } else {
            return identityCard;
        }
    }

    public static String get18IcOr15Ic(String sfzh) {
        if (StringUtils.isEmpty(sfzh)) {
            return null;
        } else if (sfzh.length() == 15) {
            return get18Ic(sfzh);
        } else {
            return sfzh.length() == 18 ? get15Ic(sfzh) : null;
        }
    }

    public static String get18Ic(String identityCard) {
        String retId = "";
        String id17 = "";
        int sum = 0;
        //int y = false;
        int[] wf = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        String[] cc = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        if (identityCard.length() != 15) {
            return identityCard;
        } else {
            id17 = identityCard.substring(0, 6) + "19" + identityCard.substring(6);

            for(int i = 0; i < 17; ++i) {
                sum += Integer.valueOf(id17.substring(i, i + 1)) * wf[i];
            }

            int y = sum % 11;
            retId = id17 + cc[y];
            return retId;
        }
    }

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
        PARITYBIT = new int[]{49, 48, 88, 57, 56, 55, 54, 53, 52, 51, 50};
        POWER_LIST = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    }
}
