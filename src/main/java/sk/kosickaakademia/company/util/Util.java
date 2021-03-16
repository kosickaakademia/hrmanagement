package sk.kosickaakademia.company.util;

import sk.kosickaakademia.company.entity.User;
import org.json.simple.*;
import sk.kosickaakademia.company.enumerator.Gender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {

    public String getJson(List<User> list){
            /* ak user==null   { }
        { "datetime":"1254-12-25..." , "size":5 , "users":[ {...},{},{},{},{} ] }
         */
        if(list==null || list.isEmpty()) return "{}";

        JSONObject object = new JSONObject();
        object.put("datetime",getCurrentDateTime());
        object.put("size",list.size());
        JSONArray jsonArray=new JSONArray();
        for(User u : list ) {
            JSONObject userJson = new JSONObject();
            userJson.put("id", u.getId());
            userJson.put("fname", u.getFname());
            userJson.put("lname", u.getLname());
            userJson.put("age", u.getAge());
            userJson.put("gender", u.getGender().toString());   // ? overit ?
            jsonArray.add(userJson);
        }
        object.put("users",jsonArray);

        return object.toJSONString();
    }
    public String getJson(User user){
        /* ak user==null   { }
        { "datetime":"1254-12-25..." , "size":1 , "users":[ {"id",    } ] }
         */
        if(user==null) return "{}";
        JSONObject object = new JSONObject();
        object.put("datetime",getCurrentDateTime());
        object.put("size",1);
        JSONArray jsonArray=new JSONArray();
        JSONObject userJson = new JSONObject();
        userJson.put("id",user.getId()) ;
        userJson.put("fname",user.getFname()) ;
        userJson.put("lname",user.getLname()) ;
        userJson.put("age",user.getAge()) ;
        userJson.put("gender",user.getGender().toString()) ;   // ? overit ?
        jsonArray.add(userJson);
        object.put("users",jsonArray);

        return object.toJSONString();
    }

    public String getCurrentDateTime(){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        return date;  // "2021-03-05 15:07:23"
    }

    public String normalizeName(String name){
        if(name==null || name.equals(""))
            return "";

        name=name.trim();
        // MILAN -> Milan       jOzEf -> Jozef
        return Character.toUpperCase(name.charAt(0))+name.substring(1).toLowerCase();
    }

    public String getOverview(List<User> list) {
        int count = list.size();
        int male = 0;
        int female = 0;
        int sumage = 0;
        int min = count>0? list.get(0).getAge():0;  // ternarny operator ... overenie ze list je empty
        int max = count>0? list.get(0).getAge():0;
        for(User u : list){
            if(u.getGender()== Gender.MALE) male++;
            else if(u.getGender()== Gender.FEMALE) female++;
            sumage+=u.getAge();
            if(min>u.getAge())
                min=u.getAge();
            if(max<u.getAge())
                max=u.getAge();
        }
        double avg=(double)sumage/count;
        JSONObject obj = new JSONObject();
        obj.put("count",count);
        obj.put("min",min);
        obj.put("max",max);
        obj.put("countMale",male);
        obj.put("countFemale",female);
        obj.put("averageAge",avg);
        return obj.toJSONString();
    }
}
