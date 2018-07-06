package com.example.hoang.project_demo_3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.example.hoang.project_demo_3.entity.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DBModel extends SQLiteAssetHelper {

    private static final String DB_NAME = "CartLocal.db";
    private static final int DB_VERSION = 1;

    public DBModel(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public List<Order> getCart() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductId","ProductName","Quantity","Price","Discount"};
        String sqlTable = "OrderDetail";

        queryBuilder.setTables(sqlTable);
        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        final  List<Order> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                result.add(new Order(cursor.getInt((cursor.getColumnIndex("ProductId"))),
                            cursor.getString(cursor.getColumnIndex("ProductName")),
                            cursor.getInt(cursor.getColumnIndex("Quantity")),
                            cursor.getString(cursor.getColumnIndex("Price"))
                        ));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String saveQuery = String.format("Insert into OrderDetail(`ProductId`,`ProductName`,`Quantity`,`Price`) VALUES ('%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());

        db.execSQL(saveQuery);
    }

    public void clearCart(){
        SQLiteDatabase db = getReadableDatabase();
        String deleteQuery = String.format("DELETE FROM OrderDetail");
        db.execSQL(deleteQuery);
    }

    public List<Order> checkExistCart(String ProductName){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductId","ProductName","Quantity","Price","Discount"};
        String sqlTable = "OrderDetail";

        queryBuilder.setTables(sqlTable);
        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        final  List<Order> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                result.add(new Order(cursor.getInt((cursor.getColumnIndex("ProductId"))),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getInt(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price"))
                ));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public void updateExistCart(int quantity, String ProductName){
        SQLiteDatabase db = getReadableDatabase();
        String updateQuery = String.format("UPDATE OrderDetail SET quantity = quantity where ProductName = ProductName");
        db.execSQL(updateQuery);
    }
}
