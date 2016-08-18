package com.source.util;

/**
 * ****************************************************************************
 * Festival.java,此类为一个节日类,包括公历和农历的节日.
 * ****************************************************************************
 */
public class Festival {
    // 公历节日 *表示放假日
    public String sFtv[] = {
    /*
	 * 以下是1月份的节日 0 1 2
	 */
            "元旦", // 0 1月1日
            "",// 1 1月6日
            "国际海关日",// 2 1月26日

			/*
			 * 以下是2月份的节日 3 4 5 6 7 8 9 10
			 */
            "世界湿地日",// 3 2月2日
            "",// 4 2月7日
            "国际气象节",// 5 2月10日
            "西方情人节",// 6 2月14日
            "",// 7 2月15日
            "",// 8 2月21日
            "",// 9 2月24日
            "",// 10 2月28日

			/*
			 * 以下是3月份的节日 11 12 13 14 15 16 17 18 19 20 21
			 */
            "国际海豹日",// 11 3月1日
            "全国爱耳日",// 12 3月3日
            "",// 13 3月5日
            "",// 14 3月6日
            "妇女节",// 15 3月8日
            "", // 16 3月12日
            "国际警察日", // 17 3月14日
            "", // 18 3月15日
            "", // 19 3月16日
            "中国国医节", // 20 3月17日
            "世界林业节", // 21 3月21日

			/*
			 * 以下是4月份的节日 22 23 24 25 26 27 28 29 30 31 32
			 */
            "国际愚人节", // 22 4月1日
            "", // 23 4月2日
            "世界卫生日", // 24 4月7日
            "", // 25 4月21日
            "世界地球日",// 26 4月22日
            "", // 27 4月23日
            "", // 28 4月24日
            "", // 29 4月25日
            "知识产权日",// 30 4月26日
            "联谊城日",// 31 4月27日
            "",// 32 4月30日

			/*
			 * 以下是5月份的节日 33 34 35 36 37 38 39 40 41 42 43 44 45
			 */
            "国际劳动节",// 33 5月1日
            "",// 34 5月3日
            "",// 35 5月4日
            "",// 36 5月8日
            "国际护士节",// 37 5月12日
            "",// 38 5月15日
            "世界电信日",// 39 5月17日
            "",// 40 5月18日
            "",// 41 5月20日
            "",// 42 5月22日
            "",// 43 5月26日
            "",// 44 5月30日
            "世界无烟日",// 45 5月31日

			/*
			 * 以下是6月份的节日 46 47 48 49 50 51 52 53 54 55 56
			 */
            "国际儿童节",// 46 6月1日
            "世界环境日",// 47 6月5日
            "全国爱眼日",// 48 6月6日
            "全国人口日",// 49 6月11日
            "",// 50 6月17日
            "",// 51 6月20日
            "",// 52 6月22日
            "",// 53 6月23日
            "",// 54 6月25日
            "",// 55 6月26日
            "",// 56 6月30日

			/*
			 * 以下是7月份的节日 57 58 59 60 61 62
			 */
            "建党节",// 57 7月1日
            "",// 58 7月2日
            "",// 59 7月7日
            "世界人口日",// 60 7月11日
            "",// 61 7月26日
            "非洲妇女日",// 62 7月31日

			/*
			 * 以下是8月份的节日 63 64 65 66 67 68
			 */
            "建军节",// 63 8月1日
            "国际电影节",// 64 8月6日
            "",// 65 8月8日
            "",// 66 8月13日
            "",// 67 8月15日
            "",// 68 8月26日

			/*
			 * 以下是9月份的节日 69 70 71 72 73 74 75 76 77
			 */
            "", // 69 9月3日
            "", // 70 9月8日
            "教师节", // 71 9月10日
            "", // 72 9月14日
            "", // 73 9月16日
            "", // 74 9月18日
            "全国爱牙日", // 75 9月20日
            "国际和平日", // 76 9月21日
            "世界旅游日", // 77 9月27日

			/*
			 * 以下是10月份的节日 78 79 80 81 82 83 84 85 86 87 88 89 90 91
			 */
            "国庆节",// 78 10月1日
            "世界动物日", // 79 10月4日
            "世界邮政日", // 80 10月9日
            "", // 81 10月10日
            "", // 82 10月11日
            "", // 83 10月13日
            "", // 84 10月14日
            "", // 85 10月15日
            "", // 86 10月16日
            "", // 87 10月17日
            "", // 88 10月22日
            "", // 89 10月24日
            "", // 90 10月28日
            "", // 91 10月31日

			/*
			 * 以下是11月份的节日 92 93 94 95 96 97
			 */
            "中国记者节", // 92 11月8日
            "消防宣传日", // 93 11月9日
            "世界青年节", // 94 11月10日
            "", // 95 11月14日
            "", // 96 11月17日
            "", // 97 11月21日

			/*
			 * 以下是12月份的节日 98 99 100 101 102 103 104 105 106 107 108 109 110 111
			 * 112 113 114 115
			 */
            "", // 98 12月1日
            "", // 99 12月2日
            "", // 100 12月3日
            "", // 101 12月4日
            "", // 102 12月5日
            "国际民航日", // 103 12月7日
            "", // 104 12月9日
            "世界人权日", // 105 12月10日
            "", // 106 12月11日
            "", // 107 12月12日
            "", // 108 12月13日
            "", // 109 12月15日
            "", // 110 12月20日
            "国际篮球日", // 111 12月21日
            "平安夜", // 112 12月24日
            "圣诞节", // 113 12月25日
            "节礼节", // 114 12月26日
            "", // 115 12月29日

            "光棍节" // 116 11月11日
    };

    // 某月的第几个星期几。 5,6,7,8 表示到数第 1,2,3,4 个星期几
    String wFtv[] = {
            "0110 黑人日",
            "0150 世界麻风日", // 一月的最后一个星期日（月倒数第一个星期日）
            "0520 国际母亲节", "0530 全国助残日", "0630 父亲节", "0911 劳动节", "0932 国际和平日",
            "0940 国际聋人节 世界儿童日", "0950 世界海事日", "1011 国际住房日",
            "1013 国际减轻自然灾害日(减灾日)", "1144 感恩节"};

    // 农历节日
    public String lFtv[] = {"春节", // 0 正月初一
            "元宵节", // 1 正月十五
            "端午节", // 2 五月初五
            "七夕节",// 3 七月初七
            "中秋节",// 4 八月十五
            "重阳节",// 5 九月初九
            "除夕",// 6 腊月最后一天
            "腊八节",// 7 腊月初八
            "小年",// 8 腊月二十三、二十四
            "苗族妹妹节",// 9 农历三月15日
            "苗族龙船节",// 10 农历五月二十四
            "彝族火把节",// 11 农历六月二十四
            "寒食",// 12 农历三月2日
            "中元节",// 13 七月十五
            "苗年",// 14 农历十月四日
            "下元节",// 15 农历十月十五

    };

    // 返回公历节日.
    public String showSFtv(int month, int day) {
        if (month == 1 && day == 1)
            return sFtv[0];
        else if (month == 1 && day == 6)
            return sFtv[1];
        else if (month == 1 && day == 26)
            return sFtv[2];

        else if (month == 2 && day == 2)
            return sFtv[3];
        else if (month == 2 && day == 7)
            return sFtv[4];
        else if (month == 2 && day == 10)
            return sFtv[5];
        else if (month == 2 && day == 14)
            return sFtv[6];
        else if (month == 2 && day == 15)
            return sFtv[7];
        else if (month == 2 && day == 21)
            return sFtv[8];
        else if (month == 2 && day == 24)
            return sFtv[9];
        else if (month == 2 && day == 28)
            return sFtv[10];

        else if (month == 3 && day == 1)
            return sFtv[11];
        else if (month == 3 && day == 3)
            return sFtv[12];
        else if (month == 3 && day == 5)
            return sFtv[13];
        else if (month == 3 && day == 6)
            return sFtv[14];
        else if (month == 3 && day == 8)
            return sFtv[15];
        else if (month == 3 && day == 12)
            return sFtv[16];
        else if (month == 3 && day == 14)
            return sFtv[17];
        else if (month == 3 && day == 15)
            return sFtv[18];
        else if (month == 3 && day == 16)
            return sFtv[19];
        else if (month == 3 && day == 17)
            return sFtv[20];
        else if (month == 3 && day == 21)
            return sFtv[21];

        else if (month == 4 && day == 1)
            return sFtv[22];
        else if (month == 4 && day == 2)
            return sFtv[23];
        else if (month == 4 && day == 7)
            return sFtv[24];
        else if (month == 4 && day == 21)
            return sFtv[25];
        else if (month == 4 && day == 22)
            return sFtv[26];
        else if (month == 4 && day == 23)
            return sFtv[27];
        else if (month == 4 && day == 24)
            return sFtv[28];
        else if (month == 4 && day == 25)
            return sFtv[29];
        else if (month == 4 && day == 26)
            return sFtv[30];
        else if (month == 4 && day == 27)
            return sFtv[31];
        else if (month == 4 && day == 30)
            return sFtv[32];

        else if (month == 5 && day == 1)
            return sFtv[33];
        else if (month == 5 && day == 3)
            return sFtv[34];
        else if (month == 5 && day == 4)
            return sFtv[35];
        else if (month == 5 && day == 8)
            return sFtv[36];
        else if (month == 5 && day == 12)
            return sFtv[37];
        else if (month == 5 && day == 15)
            return sFtv[38];
        else if (month == 5 && day == 17)
            return sFtv[39];
        else if (month == 5 && day == 18)
            return sFtv[40];
        else if (month == 5 && day == 20)
            return sFtv[41];
        else if (month == 5 && day == 22)
            return sFtv[42];
        else if (month == 5 && day == 26)
            return sFtv[43];
        else if (month == 5 && day == 30)
            return sFtv[44];
        else if (month == 5 && day == 31)
            return sFtv[45];

        else if (month == 6 && day == 1)
            return sFtv[46];
        else if (month == 6 && day == 5)
            return sFtv[47];
        else if (month == 6 && day == 6)
            return sFtv[48];
        else if (month == 6 && day == 11)
            return sFtv[49];
        else if (month == 6 && day == 17)
            return sFtv[50];
        else if (month == 6 && day == 20)
            return sFtv[51];
        else if (month == 6 && day == 22)
            return sFtv[52];
        else if (month == 6 && day == 23)
            return sFtv[53];
        else if (month == 6 && day == 25)
            return sFtv[54];
        else if (month == 6 && day == 26)
            return sFtv[55];
        else if (month == 6 && day == 30)
            return sFtv[56];

        else if (month == 7 && day == 1)
            return sFtv[57];
        else if (month == 7 && day == 2)
            return sFtv[58];
        else if (month == 7 && day == 7)
            return sFtv[59];
        else if (month == 7 && day == 11)
            return sFtv[60];
        else if (month == 7 && day == 26)
            return sFtv[61];
        else if (month == 7 && day == 31)
            return sFtv[62];

        else if (month == 8 && day == 1)
            return sFtv[63];
        else if (month == 8 && day == 6)
            return sFtv[64];
        else if (month == 8 && day == 8)
            return sFtv[65];
        else if (month == 8 && day == 13)
            return sFtv[66];
        else if (month == 8 && day == 15)
            return sFtv[67];
        else if (month == 8 && day == 26)
            return sFtv[68];

        else if (month == 9 && day == 3)
            return sFtv[69];
        else if (month == 9 && day == 8)
            return sFtv[70];
        else if (month == 9 && day == 10)
            return sFtv[71];
        else if (month == 9 && day == 14)
            return sFtv[72];
        else if (month == 9 && day == 16)
            return sFtv[73];
        else if (month == 9 && day == 18)
            return sFtv[74];
        else if (month == 9 && day == 20)
            return sFtv[75];
        else if (month == 9 && day == 21)
            return sFtv[76];
        else if (month == 9 && day == 27)
            return sFtv[77];

        else if (month == 10 && day == 1)
            return sFtv[78];
        else if (month == 10 && day == 4)
            return sFtv[79];
        else if (month == 10 && day == 9)
            return sFtv[80];
        else if (month == 10 && day == 10)
            return sFtv[81];
        else if (month == 10 && day == 11)
            return sFtv[82];
        else if (month == 10 && day == 13)
            return sFtv[83];
        else if (month == 10 && day == 14)
            return sFtv[84];
        else if (month == 10 && day == 15)
            return sFtv[85];
        else if (month == 10 && day == 16)
            return sFtv[86];
        else if (month == 10 && day == 17)
            return sFtv[87];
        else if (month == 10 && day == 22)
            return sFtv[88];
        else if (month == 10 && day == 24)
            return sFtv[89];
        else if (month == 10 && day == 28)
            return sFtv[90];
        else if (month == 10 && day == 31)
            return sFtv[91];

        else if (month == 11 && day == 11)
            return sFtv[116];
        else if (month == 11 && day == 8)
            return sFtv[92];
        else if (month == 11 && day == 9)
            return sFtv[93];
        else if (month == 11 && day == 10)
            return sFtv[94];
        else if (month == 11 && day == 14)
            return sFtv[95];
        else if (month == 11 && day == 17)
            return sFtv[96];
        else if (month == 11 && day == 21)
            return sFtv[97];

        else if (month == 12 && day == 1)
            return sFtv[98];
        else if (month == 12 && day == 2)
            return sFtv[99];
        else if (month == 12 && day == 3)
            return sFtv[100];
        else if (month == 12 && day == 4)
            return sFtv[101];
        else if (month == 12 && day == 5)
            return sFtv[102];
        else if (month == 12 && day == 7)
            return sFtv[103];
        else if (month == 12 && day == 9)
            return sFtv[104];
        else if (month == 12 && day == 10)
            return sFtv[105];
        else if (month == 12 && day == 11)
            return sFtv[106];
        else if (month == 12 && day == 12)
            return sFtv[107];
        else if (month == 12 && day == 13)
            return sFtv[108];
        else if (month == 12 && day == 15)
            return sFtv[109];
        else if (month == 12 && day == 20)
            return sFtv[110];
        else if (month == 12 && day == 21)
            return sFtv[111];
        else if (month == 12 && day == 24)
            return sFtv[112];
        else if (month == 12 && day == 25)
            return sFtv[113];
        else if (month == 12 && day == 26)
            return sFtv[114];
        else if (month == 12 && day == 29)
            return sFtv[115];
        else
            return "";// 不是节日则返回"祝你天天开心!".

    }

    // 返回农历节日. 传的值为农历值
    public String showLFtv(int month, int day, int dayOfmonth) {
        if (month == 1 && day == 1)
            return lFtv[0];// 春节
        else if (month == 1 && day == 15)
            return lFtv[1];// 正月十五
        else if (month == 5 && day == 5)// 端午节
            return lFtv[2];
        else if (month == 7 && day == 7)// 七夕节
            return lFtv[3];
        else if (month == 8 && day == 15)// 中秋节
            return lFtv[4];
        else if (month == 9 && day == 9)// 重阳节
            return lFtv[5];
        else if (month == 12 && day == 29 && dayOfmonth == 29)
            return lFtv[6];// 判断农历最后一个月是否是29天,是则这一天显示除夕.
        else if (month == 12 && day == 30 && dayOfmonth == 30)
            return lFtv[6];// 判断农历最后一个月是否是30天,是则这一天显示除夕.
        else if (month == 12 && day == 8)
            return lFtv[7];// 腊八节
        else if (month == 12 && day == 23)
            return lFtv[8];// 小年
        else if (month == 12 && day == 24)
            return lFtv[8];// 小年，各地传统不一样
        else if (month == 3 && day == 2)
            return lFtv[12];// 寒食
        else if (month == 3 && day == 15)
            return lFtv[8];// 苗族姊妹节
        else if (month == 5 && day == 24)
            return lFtv[10];// 苗族龙船节
        else if (month == 6 && day == 24)
            return lFtv[11];// 彝族火把节
        else if (month == 7 && day == 15)
            return lFtv[13];// 鬼节
        else if (month == 10 && day == 4)
            return lFtv[14];// 苗年
        else if (month == 10 && day == 15)
            return lFtv[15];// 下元节

        return "";
    }

    // 返回农历节日. 传的值为日历值
    public String showLFtvYMD(int year, int month, int day) {

        CalendarUtil cu = new CalendarUtil();
        String Mont_nl = cu.getChineseMonth(year, month, day);
        String Day_nl = cu.getChineseDay(year, month, day);

        int int_Mont_nl = 0;
        if (Mont_nl.equals("正月")) {
            int_Mont_nl = 1;
        } else if (Mont_nl.equals("二月")) {
            int_Mont_nl = 2;
        } else if (Mont_nl.equals("三月")) {
            int_Mont_nl = 3;
        } else if (Mont_nl.equals("四月")) {
            int_Mont_nl = 4;
        } else if (Mont_nl.equals("五月")) {
            int_Mont_nl = 5;
        } else if (Mont_nl.equals("六月")) {
            int_Mont_nl = 6;
        } else if (Mont_nl.equals("七月")) {
            int_Mont_nl = 7;
        } else if (Mont_nl.equals("八月")) {
            int_Mont_nl = 8;
        } else if (Mont_nl.equals("九月")) {
            int_Mont_nl = 9;
        } else if (Mont_nl.equals("十月")) {
            int_Mont_nl = 10;
        } else if (Mont_nl.equals("冬月")) {
            int_Mont_nl = 11;
        } else if (Mont_nl.equals("腊月")) {
            int_Mont_nl = 12;
        }


        int int_Day_nl = 0;

        if (Day_nl.equals("初一")) {
            int_Day_nl = 1;
        } else if (Day_nl.equals("初二")) {
            int_Day_nl = 2;
        } else if (Day_nl.equals("初三")) {
            int_Day_nl = 3;
        } else if (Day_nl.equals("初四")) {
            int_Day_nl = 4;
        } else if (Day_nl.equals("初五")) {
            int_Day_nl = 5;
        } else if (Day_nl.equals("初六")) {
            int_Day_nl = 6;
        } else if (Day_nl.equals("初七")) {
            int_Day_nl = 7;
        } else if (Day_nl.equals("初八")) {
            int_Day_nl = 8;
        } else if (Day_nl.equals("初九")) {
            int_Day_nl = 9;
        } else if (Day_nl.equals("初十")) {
            int_Day_nl = 10;
        } else if (Day_nl.equals("十一")) {
            int_Day_nl = 11;
        } else if (Day_nl.equals("十二")) {
            int_Day_nl = 12;
        } else if (Day_nl.equals("十三")) {
            int_Day_nl = 13;
        } else if (Day_nl.equals("十四")) {
            int_Day_nl = 14;
        } else if (Day_nl.equals("十五")) {
            int_Day_nl = 15;
        } else if (Day_nl.equals("十六")) {
            int_Day_nl = 16;
        } else if (Day_nl.equals("十七")) {
            int_Day_nl = 17;
        } else if (Day_nl.equals("十八")) {
            int_Day_nl = 18;
        } else if (Day_nl.equals("十九")) {
            int_Day_nl = 19;
        } else if (Day_nl.equals("二十")) {
            int_Day_nl = 20;
        } else if (Day_nl.equals("廿一")) {
            int_Day_nl = 21;
        } else if (Day_nl.equals("廿二")) {
            int_Day_nl = 22;
        } else if (Day_nl.equals("廿三")) {
            int_Day_nl = 23;
        } else if (Day_nl.equals("廿四")) {
            int_Day_nl = 24;
        } else if (Day_nl.equals("廿五")) {
            int_Day_nl = 25;
        } else if (Day_nl.equals("廿六")) {
            int_Day_nl = 26;
        } else if (Day_nl.equals("廿七")) {
            int_Day_nl = 27;
        } else if (Day_nl.equals("廿八")) {
            int_Day_nl = 28;
        } else if (Day_nl.equals("廿九")) {
            int_Day_nl = 29;
        } else if (Day_nl.equals("三十")) {
            int_Day_nl = 30;
        }

        return showLFtv(int_Mont_nl, int_Day_nl, cu.daysInChineseMonth(year - 1901 + 4598, int_Mont_nl));
    }

}