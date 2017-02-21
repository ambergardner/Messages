import com.github.mustachejava.Mustache;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    static User user;

    public static void main(String[] args) {
        Spark.init();

        ArrayList<Message> commentList = new ArrayList<>();

        Spark.get("/",
                (request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "create-user.html");
                    } else {
                        m.put("name", user.name);
                        m.put("messageboard", commentList);
                        return new ModelAndView(m, "create-messages.html");

                    }
                },
                new MustacheTemplateEngine()
        );
        Spark.post("/create-user", ((request, response) -> {
            String name = request.queryParams("loginName");
            user = new User(name);
            response.redirect("/");
            return "";
        }));
        Spark.post("/create-message", (request, response) -> {
            Message message1 = new Message(request.queryParams("messageboard"));
            commentList.add (message1);
            response.redirect("/");

            return "";




    });
}}



