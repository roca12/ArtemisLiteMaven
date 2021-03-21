package com.artemispocket.ApiConn;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CodeChefAPI {

    public static void main(String[] args) throws IOException {
        CodeChefAPI c = new CodeChefAPI();
        c.getJSONCodechef("bryanttv");
    }

    public Codechef getJSONCodechef(String codechefuser) throws MalformedURLException, IOException {
        String sURL = "https://competitive-coding-api.herokuapp.com/api/codechef/" + codechefuser;
        // Connect to the URL using java's native library
        URL url;
        url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        //get all solved 
        List<Practice> arr = new ArrayList<>();
        try {
            JsonObject fully = rootobj.getAsJsonObject("fully_solved");
            JsonArray fullyarr = fully.getAsJsonArray("Practice");
            Iterator<JsonElement> it = fullyarr.iterator();

            while (it.hasNext()) {
                JsonElement next = it.next();
                JsonObject aux = next.getAsJsonObject();
                JsonPrimitive name = aux.getAsJsonPrimitive("name");
                JsonPrimitive link = aux.getAsJsonPrimitive("link");
                arr.add(new Practice(name.getAsString(), link.getAsString()));
            }
        } catch (NullPointerException e) {
            arr.add(new Practice("No hay ejercicios resueltos", " Empieza a entrenar"));
        }

        //get parcial solved
        List<Practice> arr2 = new ArrayList<>();
        try {
            JsonObject parcial = rootobj.getAsJsonObject("partially_solved");
            JsonArray parcialarr = parcial.getAsJsonArray("Practice");
            Iterator<JsonElement> it2 = parcialarr.iterator();

            while (it2.hasNext()) {
                JsonElement next = it2.next();
                JsonObject aux = next.getAsJsonObject();
                JsonPrimitive name = aux.getAsJsonPrimitive("name");
                JsonPrimitive link = aux.getAsJsonPrimitive("link");
                arr2.add(new Practice(name.getAsString(), link.getAsString()));
            }
        } catch (NullPointerException e) {
            arr2.add(new Practice("No hay parcialmente resueltos", " :D"));
        }

        Codechef codechefinfo = new Gson().fromJson(rootobj, Codechef.class);
        codechefinfo.fully_solved.practice = arr;
        codechefinfo.partially_solved.practice = arr2;
        //System.out.println(codechefinfo.toString());
        return  codechefinfo;
    }

    public class UserDetails {

        public String name;
        public String username;
        public String country;
        public String state;
        public String city;
        public String studentProfessional;
        public String institution;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStudentProfessional() {
            return studentProfessional;
        }

        public void setStudentProfessional(String studentProfessional) {
            this.studentProfessional = studentProfessional;
        }

        public String getInstitution() {
            return institution;
        }

        public void setInstitution(String institution) {
            this.institution = institution;
        }
        
    }

    public class Contest {

        public String name;
        public int rating;
        public String global_rank;
        public String country_rank;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getGlobal_rank() {
            return global_rank;
        }

        public void setGlobal_rank(String global_rank) {
            this.global_rank = global_rank;
        }

        public String getCountry_rank() {
            return country_rank;
        }

        public void setCountry_rank(String country_rank) {
            this.country_rank = country_rank;
        }
        
    }

    public class Practice {

        public String name;
        public String link;

        public Practice(String name, String link) {
            this.name = name;
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
        

    }

    public class FullySolved {

        public int count;
        public List<Practice> practice = new ArrayList<>();

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Practice> getPractice() {
            return practice;
        }

        public void setPractice(List<Practice> practice) {
            this.practice = practice;
        }
        
    }

    public class PartiallySolved {

        public int count;
        public List<Practice> practice = new ArrayList<>();

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Practice> getPractice() {
            return practice;
        }

        public void setPractice(List<Practice> practice) {
            this.practice = practice;
        }
        
        
    }

    public class ContestRating {

        public String code;
        public String getyear;
        public String getmonth;
        public String getday;
        public String reason;
        public String penalised_in;
        public String rating;
        public String rank;
        public String name;
        public String end_date;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getGetyear() {
            return getyear;
        }

        public void setGetyear(String getyear) {
            this.getyear = getyear;
        }

        public String getGetmonth() {
            return getmonth;
        }

        public void setGetmonth(String getmonth) {
            this.getmonth = getmonth;
        }

        public String getGetday() {
            return getday;
        }

        public void setGetday(String getday) {
            this.getday = getday;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getPenalised_in() {
            return penalised_in;
        }

        public void setPenalised_in(String penalised_in) {
            this.penalised_in = penalised_in;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }
        
        
    }

    public class Codechef {
        public String status;
        public int rating;
        public String stars;
        public int highest_rating;
        public String global_rank;
        public String country_rank;
        public UserDetails user_details;
        public List<Contest> contests = new ArrayList<>();
        public List<ContestRating> contest_ratings = new ArrayList<>();
        public FullySolved fully_solved;
        public PartiallySolved partially_solved;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getHighest_rating() {
            return highest_rating;
        }

        public void setHighest_rating(int highest_rating) {
            this.highest_rating = highest_rating;
        }

        public String getGlobal_rank() {
            return global_rank;
        }

        public void setGlobal_rank(String global_rank) {
            this.global_rank = global_rank;
        }

        public String getCountry_rank() {
            return country_rank;
        }

        public void setCountry_rank(String country_rank) {
            this.country_rank = country_rank;
        }

        public UserDetails getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetails user_details) {
            this.user_details = user_details;
        }

        public List<Contest> getContests() {
            return contests;
        }

        public void setContests(List<Contest> contests) {
            this.contests = contests;
        }

        public List<ContestRating> getContest_ratings() {
            return contest_ratings;
        }

        public void setContest_ratings(List<ContestRating> contest_ratings) {
            this.contest_ratings = contest_ratings;
        }

        public FullySolved getFully_solved() {
            return fully_solved;
        }

        public void setFully_solved(FullySolved fully_solved) {
            this.fully_solved = fully_solved;
        }

        public PartiallySolved getPartially_solved() {
            return partially_solved;
        }

        public void setPartially_solved(PartiallySolved partially_solved) {
            this.partially_solved = partially_solved;
        }
        

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Codechef stats \n\n");
            sb.append("___User info___\n\n");
            sb.append("Name: " + user_details.name + "\n");
            sb.append("Country: " + user_details.country + "\n");
            sb.append("City: " + user_details.city + "\n");
            sb.append("Institution: " + user_details.institution + "\n");
            sb.append("Stars: " + stars + "\n");
            sb.append("Highest rating: " + highest_rating + "\n");
            sb.append("Global rank: " + global_rank + "\n");
            sb.append(user_details.country + " rank: " + country_rank + "\n");
            sb.append("\n\n___Problems___\n\n");
            sb.append("Fully resolved:" + fully_solved.count + " \n");
            sb.append("----------------------------------- \n");
            sb.append("Name : link \n");
            for (int i = 0; i < fully_solved.practice.size(); i++) {
                sb.append(fully_solved.practice.get(i).name + "\t : " + fully_solved.practice.get(i).link + "\n");
            }

            sb.append("\nPartial resolved:" + partially_solved.count + " \n");
            sb.append("----------------------------------- \n");
            for (int i = 0; i < partially_solved.practice.size(); i++) {
                sb.append(partially_solved.practice.get(i).name + " : " + partially_solved.practice.get(i).link + "\n");
            }
            sb.append("\n\n___Contests___\n\n");
            for (int i = 0; i < contests.size(); i++) {
                sb.append("Name: " + contests.get(i).name + "\n");
                sb.append("Rating: " + contests.get(i).rating + "\n");
                sb.append("Global rank: " + contests.get(i).global_rank + "\n");
                sb.append(user_details.country + " rank: " + contests.get(i).country_rank + "\n\n");
            }
            sb.append("\n\n___Contests details___\n\n");
            if (contest_ratings.size() == 0) {
                sb.append("No participation in CodeChef contest");
            } else {
                for (int i = 0; i < contest_ratings.size(); i++) {
                    sb.append("Code: " + contest_ratings.get(i).code + "\n");
                    sb.append("Date: " + contest_ratings.get(i).getday + "/" + contest_ratings.get(i).getmonth + "/" + contest_ratings.get(i).getyear + "\n");
                    sb.append("Contest Name: " + contest_ratings.get(i).name + "\n");
                    sb.append("End date: " + contest_ratings.get(i).end_date + "\n");
                    sb.append("Rank: " + contest_ratings.get(i).rank + "\n");
                    sb.append("Rating: " + contest_ratings.get(i).rating + "\n");
                }
            }
            return sb.toString();
        }

    }
}
