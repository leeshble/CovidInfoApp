package com.corona.coronaapp.system;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    final static String DB_NAME = "Corona.db";
    final static int DB_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "CREATE TABLE Destination ( Destination_ID INTEGER NOT NULL, Destination_name CHAR(500) NOT NULL PRIMARY KEY, Local_ID INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(qry);
        qry = "CREATE TABLE Current_Info(Info_ID INTEGER NOT NULL PRIMARY KEY, Local_ID INTEGER NOT NULL, Grade CHAR(500) NOT NULL)";
        sqLiteDatabase.execSQL(qry);
        qry = "CREATE TABLE Guideline(Grade CHAR(10) NOT NULL PRIMARY KEY, GuideText CHAR(1000))";
        sqLiteDatabase.execSQL(qry);
        qry = "CREATE TABLE News(News_ID INTEGER NOT NULL PRIMARY KEY, News_Info CHAR(500) NOT NULL, News_URL CHAR(500) NOT NULL)";
        sqLiteDatabase.execSQL(qry);
        qry = "CREATE TABLE Setting(Setting_Location INTEGER NOT NULL, Local_ID INTEGER NOT NULL, Setting_ID INTEGER NOT NULL PRIMARY KEY)";
        sqLiteDatabase.execSQL(qry);

        sqLiteDatabase.execSQL("CREATE TABLE City_Info(Info_ID INTEGER NOT NULL PRIMARY KEY, Local_ID INTEGER NOT NULL, Grade CHAR(500) NOT NULL)");
        for (int i = 0; i < 17; i++) {
            sqLiteDatabase.execSQL("INSERT INTO City_Info(Local_ID, Grade) VALUES ( '"+ i +"' , '1')");
        }

        sqLiteDatabase.execSQL("CREATE TABLE list(id INTEGER PRIMARY KEY, title CHAR(200) NOT NULL, position INTEGER NOT NULL)");

        qry = "INSERT INTO Setting(Setting_Location, Local_ID) VALUES (0, 1)";
        sqLiteDatabase.execSQL(qry);

        sqLiteDatabase.execSQL("INSERT INTO Current_Info(Local_ID, Grade) VALUES ('1', '1')");

        sqLiteDatabase.execSQL("INSERT INTO Guideline(Grade, GuideText) VALUES ('1', '\"□ 중점관리시설: 이용인원 제한 등 핵심방역수칙 의무화\n" +
                "□ 일반관리시설: 정상 운영 기본 방역수칙 3가지 의무화\n" +
                "□ 국공립시설 : 경륜,경마 등 50% 인원제한\n" +
                "□ 사회복지시설(어린이집 포함) : 철저한 방역 하에 운영\n" +
                "□ 모임,행사: 500명 이상 행사는 지자체 신고,협의 필요\n" +
                "□ 스포츠관람: 관중 입장(50%)\n" +
                "□ 교통시설 이용 : 마스크 착용 의무화\n" +
                "□ 등교: 밀집도 2/3 원칙, 조정가능\n" +
                "□ 종교활동: 좌석 한칸 띄우기 모임,식사 자제권고(숙박 행사금지)\n" +
                "□ 직장근무: 기관,부서별 적정 비율 재택근무 등 실시권고\"\n')");

        sqLiteDatabase.execSQL("INSERT INTO Guideline(Grade, GuideText) VALUES ('1.5', '\"□ 중점관리시설: 이용인원 제한 강화, 위험도 높은 활동 금지\n" +
                "□ 일반관리시설: 시설별 특성에 따라 이용인원 제한\n" +
                "□ 국공립시설 : 경륜,경마 등 20%,이외 시설 50% 인원제한\n" +
                "□ 사회복지시설(어린이집 포함) : 철저한 방역 하에 운영\n" +
                "□ 모임,행사: 500명 이상 행사는 지자체 신고,협의 필요, 축제 등 일부 행사는 100인이상 금지\n" +
                "□ 스포츠관람: 관중 입장(30%)\n" +
                "□ 교통시설 이용 : 마스크 착용 의무화\n" +
                "□ 등교: 밀집도 2/3 준수\n" +
                "□ 종교활동: 정규예배 등 좌석수의 30%이내로 제한 모임,식사 금지\n" +
                "□ 직장근무: 기관,부서별 재택근무 등확대 권고\"\n')");

        sqLiteDatabase.execSQL("INSERT INTO Guideline(Grade, GuideText) VALUES ('2', '\"□ 중점관리시설: 유흥시설 5종 집합금지, 이외 시설은 21시 이후 운영 중단 등 제한 강화, 위반시 원스트라이크아웃제\n" +
                "□  일반관리시설: 이용인원 제한 강화, 위험도 높은 활동 금지\n" +
                "□ 국공립시설 : 경륜,경마 등 중단, 이외시설 30% 인원 제한\n" +
                "□ 사회복지시설(어린이집 포함) : 철저한 방역 하에 운영\n" +
                "□ 모임,행사: 100인이상 금지\n" +
                "□ 스포츠관람: 관중 입장(10%)\n" +
                "□ 교통시설 이용 : 교통수단(차량)내 음식섭취금지 추가(국제항공편제외)\n" +
                "□ 등교: 밀집도 1/3 원칙(고등학교2/3) 최대 2/3내에서 운영가능\n" +
                "□ 종교활동: 정규예배 등 좌석수의 20%이내로 제한 모임,식사 금지\n" +
                "□ 직장근무: 기관,부서별 재택근무 등 확대 권고\"\n')");

        sqLiteDatabase.execSQL("INSERT INTO Guideline(Grade, GuideText) VALUES ('2.5', '\"□ 중점관리시설: 방문판매 등 직접판매 홍보관, 노래연습장, 실내 스탠딩공연장, 집합금지\n" +
                "□일반관리시설: 21시 이후 운영 중단 등 제한 강화, 위반 시 원스트라이크아웃제\n" +
                "□ 국공립시설 : 체육시설, 경륜,경마등 운영 중단, 이외시설 30% 인원제한\n" +
                "□ 사회복지시설(어린이집 포함) : 철저한 방역 하에 운영\n" +
                "□ 모임,행사: 50인 이상 금지\n" +
                "□ 스포츠관람: 무관중 경기\n" +
                "□ 교통시설 이용 : KTX,고속버스 등 50%이내로 예매 제한 권고(항공기 제외)\n" +
                "□ 등교: 밀집도 1/3 준수\n" +
                "□ 종교활동: 비대면, 20명이내로 인원 제한모임식사 금지\n" +
                "□ 직장근무: 인원의 1/3이상 재택근무 등 권고 \"\n')");

        sqLiteDatabase.execSQL("INSERT INTO Guideline(Grade, GuideText) VALUES ('3', '\"□ 중점관리시설, 일반관리시설: 필수시설 외 집합금지 이외 시설도 운영 제한\n" +
                "□ 국공립시설 : 실내외 구분없이 운영중단\n" +
                "□ 사회복지시설(어린이집 포함) : 휴관,휴원 권고 긴급돌봄 등 유지\n" +
                "□ 모임,행사: 10인 이상 금지\n" +
                "□ 스포츠관람: 경기 중단\n" +
                "□ 교통시설 이용 : KTX,고속버스 등 50%이내로 예매 제한 (항공기 제외)\n" +
                "□ 등교: 원격수업 전환\n" +
                "□ 종교활동: 1인 영상만 허용 모임식사 금지\n" +
                "□ 직장근무: 필수인력 이외 재택근무 등 의무화\"\n')");


        sqLiteDatabase.execSQL("INSERT INTO News(News_Info, News_URL) VALUES ('테스트', '" +"1"+ "')");
        sqLiteDatabase.execSQL("INSERT INTO News(News_Info, News_URL) VALUES ('테스트', '" +"1"+ "')");
        sqLiteDatabase.execSQL("INSERT INTO News(News_Info, News_URL) VALUES ('테스트', '" +"1"+ "')");
        sqLiteDatabase.execSQL("INSERT INTO News(News_Info, News_URL) VALUES ('테스트', '" +"1"+ "')");
        sqLiteDatabase.execSQL("INSERT INTO News(News_Info, News_URL) VALUES ('테스트', '" +"1"+ "')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS list");
        onCreate(sqLiteDatabase);
    }
}