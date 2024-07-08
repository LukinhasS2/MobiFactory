package com.pucpr.mobifactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mobifactory.db";
    private static final int DATABASE_VERSION = 2;

    // Tabela de usuários
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USER_LEVEL = "user_level";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";

    // Tabela de pedidos
    public static final String TABLE_PEDIDOS = "pedidos";
    public static final String COLUMN_PEDIDO_ID = "id";
    public static final String COLUMN_CLIENTE = "cliente";
    public static final String COLUMN_DATA_ENTREGA = "dataEntrega";
    public static final String COLUMN_STATUS = "status";

    // SQL para criar a tabela de usuários
    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_USER_LEVEL + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PHONE + " TEXT" +
                    ");";

    // SQL para criar a tabela de pedidos
    private static final String TABLE_CREATE_PEDIDOS =
            "CREATE TABLE " + TABLE_PEDIDOS + " (" +
                    COLUMN_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CLIENTE + " TEXT, " +
                    COLUMN_DATA_ENTREGA + " TEXT, " +
                    COLUMN_STATUS + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PEDIDOS);
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDOS);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        insertUser(db, "admin", "admin", "admin", "admin@hotmail.com.com", "1");
        insertUser(db, "funcionario", "123", "employee", "funcionario@hotmail.com", "2");
    }

    private void insertUser(SQLiteDatabase db, String username, String password, String userLevel, String email, String phone) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_USER_LEVEL, userLevel);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        db.insert(TABLE_USERS, null, values);
    }

    // Métodos CRUD para a tabela de pedidos
    public long addPedido(Pedido pedido) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTE, pedido.getCliente());
        values.put(COLUMN_DATA_ENTREGA, pedido.getDataEntrega());
        values.put(COLUMN_STATUS, pedido.getStatus());
        return db.insert(TABLE_PEDIDOS, null, values);
    }

    public List<Pedido> getAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PEDIDOS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_ID));
                String cliente = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLIENTE));
                String dataEntrega = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_ENTREGA));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS));
                pedidos.add(new Pedido(id, cliente, dataEntrega, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pedidos;
    }
}