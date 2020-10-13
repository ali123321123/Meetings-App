package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mappe2.Modul.Mote;
import com.example.mappe2.Modul.Person;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Navn
    private static final String DATABASE_NAME = "MotePersonDb";


    // Tabller Navn
    private static final String TABLE_Person = "Person";
    private static final String TABLE_Mote = "Mote";
    private static final String TABLE_Person_Mote= "Person_Mote";


  //Kolennene navn til Person Tabell

    private static final String Person_ID ="person_ID";
    private static final String Navn ="navn";
    private static final String Telefonnr ="telefonnr";
  //  private static final String img ="img";

    //Kolennene navn til Mote Tabell

    private static final String Mote_ID ="Mote_ID";
    private static final String Tittel="Tittel";
    private static final String Type="Type";
    private static final String Dato="Dato";
    private static final String Sted="Sted";



    //Kolennene til koblingstabell
    private static final String Person_Mote_ID ="P_M_ID";


    // Table Create Statements
    private static final String CREATE_TABLE_Person = "CREATE TABLE "
            + TABLE_Person  + "(" +  Person_ID + " INTEGER PRIMARY KEY," + Navn
            + " TEXT,"+Telefonnr+"TEXT" + ")";

    // Møte table create statement
    private static final String CREATE_TABLE_Mote= "CREATE TABLE " + TABLE_Mote
            + "(" + Mote_ID + " INTEGER PRIMARY KEY," + Tittel + " TEXT,"+ Type + " TEXT,"+Sted + " TEXT,"
            + Dato + " DATETIME" + ")";


    // Person_tag table create statement
    private static final String CREATE_TABLE_PERSON_MOTE = "CREATE TABLE "
            + TABLE_Person_Mote + "(" + Person_Mote_ID + " INTEGER PRIMARY KEY,"
            + Person_ID + " INTEGER," + Mote_ID + " INTEGER"
            + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Lage Tabeller
        db.execSQL(CREATE_TABLE_Person);
        db.execSQL(CREATE_TABLE_Mote);
        db.execSQL(CREATE_TABLE_PERSON_MOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Person);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Mote );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Person_Mote);

        // lage nye tabeller
        onCreate(db);
    }


//Legge Til Person
    public long LeggeTilPerson(Person person, long[] moter_ider) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Navn, person.getNavn());
        values.put(Telefonnr , person.getTelefonnr());
    //    values.put(img, person.getImg());


        long Person_id = db.insert(TABLE_Person, null, values);


        for (long id : moter_ider) {
            createMotePerson(Person_id, id);
        }

        return Person_id;
    }


    //Hente Person
    public Person getPerson(long person_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+TABLE_Person+"WHERE"+Person_ID+"="+person_id;

        Cursor c = db.rawQuery(selectQuery,null);

        if(c != null)
            c.moveToFirst();

        Person person = new Person();
        person.setPersonId(c.getInt(c.getColumnIndex(Person_ID)));
        person.setNavn(c.getString(c.getColumnIndex(Navn)));
        person.setTelefonnr(c.getString(c.getColumnIndex(Telefonnr)));
       // person.setImg(c.getInt(c.getColumnIndex(img)));

        return person;

    }


    //Hente alle Personer

    public List<Person> HenteAllePersoner() {
        List<Person> personer = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM " + TABLE_Person;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        //går gjennom alle rader og legge til listen
        if (c.moveToFirst()) {
            do {
                Person person = new Person();
                person.setPersonId(c.getInt(c.getColumnIndex(Person_ID)));
                person.setNavn(c.getString(c.getColumnIndex(Navn)));
                person.setTelefonnr(c.getString(c.getColumnIndex(Telefonnr)));
        //        person.setImg(c.getInt(c.getColumnIndex(img)));


                personer.add(person);
            } while (c.moveToNext());
        }

        return personer;
    }



    //Henter alle personer under en møte
    public List<Person> HenteAllePersonerIMote(int  moteId) {
        List<Person> personer = new ArrayList<Person>();

        String selectQuery = "SELECT  * FROM " + TABLE_Person + " td, "
                + TABLE_Mote + " tg, " + TABLE_Person_Mote + " tt WHERE tg."
                + Mote_ID + " = '" + moteId + "'" + " AND tg." + Person_ID
                + " = " + "tt." + Person_ID + " AND td." + Mote_ID+ " = "
                + "tt." + Mote_ID;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        if (c.moveToFirst()) {
            do {

                Person person = new Person();
                person.setPersonId(c.getInt(c.getColumnIndex(Person_ID)));
                person.setNavn(c.getString(c.getColumnIndex(Navn)));
                person.setTelefonnr(c.getString(c.getColumnIndex(Telefonnr)));
           //     person.setImg(c.getInt(c.getColumnIndex(img)));


                personer.add(person);
            } while (c.moveToNext());
        }

        return personer;
    }



    /*
     * Oppdater en Person
     */
    public int OppdaterePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Navn, person.getNavn());
        values.put(Telefonnr, person.getTelefonnr());
     //   values.put(img, person.getImg());
        // Oppdatere raden
        return db.update(TABLE_Person, values, Person_ID + " = ?",
                new String[] { String.valueOf(person.getPersonId()) });
    }



    //Slette en person
    public void SlettePerson(long person_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Person, Person_ID + " = ?",
                new String[] { String.valueOf(person_id) });
    }


    //Lage en møte

    public long createMote(Mote mote) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Tittel,mote.getNavn());
        values.put(Type, mote.getType());
        values.put(Dato, mote.getDato());
        values.put(Sted,mote.getSted());

        // insert row
        long mote_id = db.insert(TABLE_Mote, null, values);

        return mote_id;
    }


    //Hente alle Mæter


    public List<Mote> HenteAlleMoter() {
        List<Mote> moter = new ArrayList<Mote>();
        String selectQuery = "SELECT  * FROM " + TABLE_Mote;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Mote mote = new Mote();
                mote.setMoteId(c.getInt(c.getColumnIndex(Mote_ID)));
                mote.setNavn(c.getString(c.getColumnIndex(Tittel)));
                mote.setSted(c.getString(c.getColumnIndex(Sted)));
                mote.setType(c.getString(c.getColumnIndex(Type)));
                mote.setDato(c.getString(c.getColumnIndex(Dato)));



                // legge til  moter list
                moter.add(mote);
            } while (c.moveToNext());
        }
        return moter;
    }


    //Oppdatere en møte

    public int OppdatereMote(Mote mote) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Tittel, mote.getNavn());
        values.put(Type,mote.getType());
        values.put(Dato,mote.getDato());
        values.put(Sted,mote.getSted());



        // updating row
        return db.update(TABLE_Mote, values, Mote_ID + " = ?",
                new String[] { String.valueOf(mote.getMoteId()) });
    }








    public long createMotePerson(long person_id, long mote_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Person_ID, person_id);
        values.put(Mote_ID, mote_id);


        long id = db.insert(TABLE_Person_Mote, null, values);

        return id;
    }

    //Slette et møte til en person

    public int SletteMotetilPerson(long id,long mote_id){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Mote_ID,mote_id);

        return db.update(TABLE_Person, values, Person_ID+ " = ?",
                new String[] { String.valueOf(id) });
    }


    //Oppdater et møte til en person


    public int OppdaterMoteTilPerson(long id, long mote_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Mote_ID, mote_id);

        // updating row
        return db.update(TABLE_Person, values, Person_ID + " = ?",
                new String[] { String.valueOf(id) });
    }


    //Slutte Databasen

    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }































}
