/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Варвара
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Practical task 2.2, Kolotilina Varvara, RIBO-03-22");
        System.out.println("Start program!");
        
        
        /*String server = "https://www.mirea.ru/";
        HTTPRunnable httpRunnable = new HTTPRunnable (server, null);
        Thread th = new Thread (httpRunnable);
        th.start();
        try {
            th. join ();
        } catch (InterruptedException ex) {
            
        }finally{
            try{
                FileWriter fw = new FileWriter ("D: \\Work\\MIREA\\resp.html");
                fw.write (httpRunnable.getResponseBody ());
                fw.close ();
                System.out.println("Success save response from server:" + server);
            } catch (IOException ex) {
                System.out.println("Error response saving : " + ex.getMessage ());

            }
        }*/
        
        
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String, String> map = new HashMap();
        map.put("name", "Kolotilina");
        map.put("group", "RIBO-03-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try {
            th.join();
        } catch (InterruptedException ex) {
            
        } finally{
            try{
                JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
                int result = jSONObject.getInt("result_code");
                System.out.println("Result: " + result);
                System.out.println("Type: " + jSONObject.getString("message_type"));
                System.out.println("Text: " + jSONObject.getString("message_text"));
                switch (result) {
                    case 1: //сервер вернул корректные данные
                        JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                        System.out.println("Task list:");
                        for(int i = 0; i < jSONArray.length(); i++){
                            System.out.println((i + 1) + ") " + jSONArray.getString(i));
                        }
                        break;
                    case 0: 
                        System.out.println("Error!");
                        break;
                    default:
                        break;
                
                }
            }
            catch(JSONException je){
                System.out.println("One field is not filled in!");
            }
        }    
    }
}
