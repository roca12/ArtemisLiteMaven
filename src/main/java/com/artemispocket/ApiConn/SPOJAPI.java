package com.artemispocket.ApiConn;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class SPOJAPI {

    

    public SpojUserStats  getJSONSPOJ(String spojuser) throws MalformedURLException, IOException {
        String sURL = "https://competitive-coding-api.herokuapp.com/api/spoj/" + spojuser;
        // Connect to the URL using java's native library
        URL url;
        url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        SpojUserStats spojinfo = new Gson().fromJson(rootobj, SpojUserStats.class);
        //System.out.println(spojinfo.toString());
        return spojinfo;

    }

    public class SpojUserStats {

        public String status;
        public String username;
        public String platform;
        public double points;
        public int rank;
        public List<String> solved;
        public List<String> todo;
        public String joindata;
        public String institute;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public double getPoints() {
            return points;
        }

        public void setPoints(double points) {
            this.points = points;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public List<String> getSolved() {
            return solved;
        }

        public void setSolved(List<String> solved) {
            this.solved = solved;
        }

        public List<String> getTodo() {
            return todo;
        }

        public void setTodo(List<String> todo) {
            this.todo = todo;
        }

        public String getJoindata() {
            return joindata;
        }

        public void setJoindata(String joindata) {
            this.joindata = joindata;
        }

        public String getInstitute() {
            return institute;
        }

        public void setInstitute(String institute) {
            this.institute = institute;
        }
        
        
        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();
            sb.append("Codechef stats \n\n");
            sb.append("___User info___\n\n");
            sb.append("Name: " + username + "\n");
            sb.append("Institute: " + institute + "\n");
            sb.append("Join date: " + joindata + "\n");
            sb.append("Points: " + points + "\n");
            sb.append("Rank: " + rank + "\n\n");
            sb.append("Problems resolved:\n");
            for (String s : solved) {
                sb.append(s+"\t: https://www.spoj.com/problems/"+s+"\n");
            }
            sb.append("\nTo do:\n");
            for (String s : todo) {
                sb.append(s+"\t: https://www.spoj.com/problems/"+s+"\n");
            }
            return sb.toString();
        }
    }
}
