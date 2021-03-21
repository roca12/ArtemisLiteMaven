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
import java.util.HashSet;
import java.util.List;

public class CodeforcesAPI {

    public static class Pair {

        UserInfo i;
        Submissions s;

        public Pair(UserInfo i, Submissions s) {
            this.i = i;
            this.s = s;
        }

        public UserInfo getI() {
            return i;
        }

        public void setI(UserInfo i) {
            this.i = i;
        }

        public Submissions getS() {
            return s;
        }

        public void setS(Submissions s) {
            this.s = s;
        }

    }

    public Pair getJSONCodeforces(String codeforcesuser) throws MalformedURLException, IOException {
        String sURL = "https://codeforces.com/api/user.info?handles=" + codeforcesuser;
        // Connect to the URL using java's native library
        URL url;
        url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        UserInfo codeforcesinfo = new Gson().fromJson(rootobj, UserInfo.class);
        Submissions codeforcesinfosubmissions = getJSONCodeforcesSub(codeforcesuser);
        //System.out.println(codeforcesuser+" "+codeforcesinfo.toString());
        //  System.out.println(codeforcesinfosubmissions.toString());
        return new Pair(codeforcesinfo, codeforcesinfosubmissions);
    }

    public Submissions getJSONCodeforcesSub(String codeforcesuser) throws MalformedURLException, IOException {
        String sURL = "https://codeforces.com/api/user.status?handle=" + codeforcesuser;
        // Connect to the URL using java's native library
        URL url;
        url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        Submissions s = new Gson().fromJson(rootobj, Submissions.class);
        return s;
    }

    public class UserInfoResult {

        public String lastName;
        public String country;
        public int lastOnlineTimeSeconds;
        public String city;
        public int rating;
        public int friendOfCount;
        public String titlePhoto;
        public String handle;
        public String avatar;
        public String firstName;
        public int contribution;
        public String organization;
        public String rank;
        public int maxRating;
        public int registrationTimeSeconds;
        public String maxRank;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getLastOnlineTimeSeconds() {
            return lastOnlineTimeSeconds;
        }

        public void setLastOnlineTimeSeconds(int lastOnlineTimeSeconds) {
            this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getFriendOfCount() {
            return friendOfCount;
        }

        public void setFriendOfCount(int friendOfCount) {
            this.friendOfCount = friendOfCount;
        }

        public String getTitlePhoto() {
            return titlePhoto;
        }

        public void setTitlePhoto(String titlePhoto) {
            this.titlePhoto = titlePhoto;
        }

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public int getContribution() {
            return contribution;
        }

        public void setContribution(int contribution) {
            this.contribution = contribution;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getMaxRating() {
            return maxRating;
        }

        public void setMaxRating(int maxRating) {
            this.maxRating = maxRating;
        }

        public int getRegistrationTimeSeconds() {
            return registrationTimeSeconds;
        }

        public void setRegistrationTimeSeconds(int registrationTimeSeconds) {
            this.registrationTimeSeconds = registrationTimeSeconds;
        }

        public String getMaxRank() {
            return maxRank;
        }

        public void setMaxRank(String maxRank) {
            this.maxRank = maxRank;
        }

    }

    public class UserInfo {

        public String status;
        public List<UserInfoResult> result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<UserInfoResult> getResult() {
            return result;
        }

        public void setResult(List<UserInfoResult> result) {
            this.result = result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Codeforces stats \n");
            sb.append("\n _______User info_______ \n\n");
            sb.append("Name: " + result.get(0).firstName + " " + result.get(0).lastName + "\n");
            sb.append("Country: " + result.get(0).country + "\n");
            sb.append("City: " + result.get(0).city + "\n");
            sb.append("Institution: " + result.get(0).organization + "\n");
            sb.append("Avatar: " + result.get(0).avatar + "\n");
            sb.append("Title photo: " + result.get(0).titlePhoto + "\n");
            sb.append("rank: " + result.get(0).rank + "\n");
            sb.append("max rank: " + result.get(0).maxRank + "\n");

            return sb.toString();
        }

    }

    public class Problem {

        public int contestId;
        public String index;
        public String name;
        public String type;
        public int points;
        public int rating;
        public List<String> tags;

        public int getContestId() {
            return contestId;
        }

        public void setContestId(int contestId) {
            this.contestId = contestId;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

    }

    public class Member {

        public String handle;

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

    }

    public class Author {

        public int contestId;
        public List<Member> members;
        public String participantType;
        public boolean ghost;
        public int startTimeSeconds;
        public int room;

        public int getContestId() {
            return contestId;
        }

        public void setContestId(int contestId) {
            this.contestId = contestId;
        }

        public List<Member> getMembers() {
            return members;
        }

        public void setMembers(List<Member> members) {
            this.members = members;
        }

        public String getParticipantType() {
            return participantType;
        }

        public void setParticipantType(String participantType) {
            this.participantType = participantType;
        }

        public boolean isGhost() {
            return ghost;
        }

        public void setGhost(boolean ghost) {
            this.ghost = ghost;
        }

        public int getStartTimeSeconds() {
            return startTimeSeconds;
        }

        public void setStartTimeSeconds(int startTimeSeconds) {
            this.startTimeSeconds = startTimeSeconds;
        }

        public int getRoom() {
            return room;
        }

        public void setRoom(int room) {
            this.room = room;
        }

    }

    public class SubmissionsResult {

        public int id;
        public int contestId;
        public int creationTimeSeconds;
        public long relativeTimeSeconds;
        public Problem problem;
        public Author author;
        public String programmingLanguage;
        public String verdict;
        public String testset;
        public int passedTestCount;
        public int timeConsumedMillis;
        public int memoryConsumedBytes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getContestId() {
            return contestId;
        }

        public void setContestId(int contestId) {
            this.contestId = contestId;
        }

        public int getCreationTimeSeconds() {
            return creationTimeSeconds;
        }

        public void setCreationTimeSeconds(int creationTimeSeconds) {
            this.creationTimeSeconds = creationTimeSeconds;
        }

        public long getRelativeTimeSeconds() {
            return relativeTimeSeconds;
        }

        public void setRelativeTimeSeconds(long relativeTimeSeconds) {
            this.relativeTimeSeconds = relativeTimeSeconds;
        }

        public Problem getProblem() {
            return problem;
        }

        public void setProblem(Problem problem) {
            this.problem = problem;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getProgrammingLanguage() {
            return programmingLanguage;
        }

        public void setProgrammingLanguage(String programmingLanguage) {
            this.programmingLanguage = programmingLanguage;
        }

        public String getVerdict() {
            return verdict;
        }

        public void setVerdict(String verdict) {
            this.verdict = verdict;
        }

        public String getTestset() {
            return testset;
        }

        public void setTestset(String testset) {
            this.testset = testset;
        }

        public int getPassedTestCount() {
            return passedTestCount;
        }

        public void setPassedTestCount(int passedTestCount) {
            this.passedTestCount = passedTestCount;
        }

        public int getTimeConsumedMillis() {
            return timeConsumedMillis;
        }

        public void setTimeConsumedMillis(int timeConsumedMillis) {
            this.timeConsumedMillis = timeConsumedMillis;
        }

        public int getMemoryConsumedBytes() {
            return memoryConsumedBytes;
        }

        public void setMemoryConsumedBytes(int memoryConsumedBytes) {
            this.memoryConsumedBytes = memoryConsumedBytes;
        }

    }

    public class Submissions {

        public String status;
        public List<SubmissionsResult> result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<SubmissionsResult> getResult() {
            return result;
        }

        public void setResult(List<SubmissionsResult> result) {
            this.result = result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\n___Problems___\n\n");
            sb.append("----------------------------------- \n");
            int accepted = 0;
            HashSet<String> set = new HashSet<>();
            for (SubmissionsResult i : result) {
                if (i.verdict.equals("OK") && set.add(i.problem.name)) {
                    sb.append("Problem name: " + i.problem.name + "\n");
                    sb.append("Problems tags:");
                    for (String s : i.problem.tags) {
                        sb.append(s + ",");
                    }
                    sb.append("\n");
                    sb.append("Language: " + i.programmingLanguage + "\n");
                    sb.append("\n");
                    accepted++;
                }
            }
            sb.append("Problems solved: " + accepted + "\n\n");
            sb.append("Total submits: " + result.size() + "\n\n");
            sb.append("Accuracy: " + (((double) accepted / result.size()) * 100) + "%");
            sb.append("\n\n");
            return sb.toString();
        }
    }
}
