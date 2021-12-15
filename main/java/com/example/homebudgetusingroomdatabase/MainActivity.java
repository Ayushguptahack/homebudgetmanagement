package com.example.homebudgetusingroomdatabase;


//NOtes:
// double click on the list item to delete data from database
//use negative values as expense
//insert same description with different value will update the value

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.homebudgetusingroomdatabase.Room.MyDatabase;
import com.example.homebudgetusingroomdatabase.Room.expense;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView rem_balance;
    TextView total_income;
    TextView total_expense;
    EditText desc;
    EditText val;               //variable initialization
    Button btn;
    ListView lv;
    MyDatabase myDatabase;

    ArrayList<expense>  arr  =  new ArrayList<>();
    HashSet<String> hs =  new HashSet<>();
    ExpenseListAdapter  explist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rem_balance = findViewById(R.id.remaining_balance);
        total_income = findViewById(R.id.total_income);
        total_expense = findViewById(R.id.total_expense);       //Conecting components with java
        desc = findViewById(R.id.desc);
        val = findViewById(R.id.val);
        btn = findViewById(R.id.btn);
        lv = findViewById(R.id.lv);

        dbinitialiser();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = desc.getText().toString();
                String inival = val.getText().toString();

                int inc = 0;
                int  bal = 0;
                int total = 0;

                if(inival.equals("") || isNumeric(inival) == false){
                    Toast.makeText(MainActivity.this, "Value canot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int value = Integer.parseInt(val.getText().toString());
                if(value == 0 || data.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter correct values.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if(hs.contains(data)){
                        myDatabase.dao().updateUser(data,inival); //updating database
                        if(true) {
                            for (int i = 0; i < arr.size(); i++) {
                                if (arr.get(i).description.equals(data)) {
                                    arr.get(i).value = value + "";
                                }
                            }
                                Toast.makeText(MainActivity.this, "Data updated successfully.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Error in updating  data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    else {
                        expense exp = new expense(data,inival);
                        myDatabase.dao().insertData(exp);  //adding data to database

                        if(true){
                            Toast.makeText(MainActivity.this, "Data inserted Successfully.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Oops error occured.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                List<expense> userdata = myDatabase.dao().getUser();
                for(int i = 0 ; i < userdata.size(); i++){
                    expense exp = new expense(userdata.get(i).description,userdata.get(i).value);
                    int val = Integer.parseInt(userdata.get(i).value);
                    String st = userdata.get(i).description;
                    if(val > 0){
                        inc += val;
                    }else {
                        bal += val;
                    }
                    if(hs.contains(st)){
                        continue;
                    }else{
                        hs.add(st);
                        arr.add(exp);
                    }
                }
                total = inc + bal;
                explist = new ExpenseListAdapter(getApplicationContext()
                        ,R.layout.adapter_view_layout,arr);
                lv.setAdapter(explist);

                rem_balance.setText(total+"");
                total_income.setText(inc+"");
                total_expense.setText(bal+"");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myDatabase.dao().deletedata(arr.get(i).description); //deleting data from database
                int inc = 0;
                int bal = 0;
                int total = 0;
                if(true){
                    hs.remove(arr.get(i).description);
                    arr.remove(i);
                    explist.notifyDataSetChanged();
                    lv.setAdapter(explist);

                    rem_balance.setText(0+"");
                    total_income.setText(0+"");
                    total_expense.setText(0+"");

                    List<expense> userdata = myDatabase.dao().getUser();
                    for(int j = 0 ; j < userdata.size(); j++){
                        expense exp = new expense(userdata.get(j).description,userdata.get(j).value);
                        int val = Integer.parseInt(userdata.get(j).value);
                        String st = userdata.get(j).description;
                        if(val > 0){
                            inc += val;
                        }else {
                            bal += val;
                        }
                        if(hs.contains(st)){
                            continue;
                        }else{
                            hs.add(st);
                            arr.add(exp);
                        }
                    }
                    total = inc + bal;

                    rem_balance.setText(total+"");
                    total_income.setText(inc+"");
                    total_expense.setText(bal+"");
                    Toast.makeText(MainActivity.this, "Data  deleted successfully.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error in deleting dxata.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private boolean isNumeric(String str){
        try{
            int i = Integer.parseInt(str);
            return true;
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Please Enter a valid number.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void dbinitialiser(){
        myDatabase = Room.databaseBuilder(MainActivity.this,MyDatabase.class,"UserDb").allowMainThreadQueries().build();
    }
}