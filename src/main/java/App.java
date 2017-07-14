import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (req, res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/administrator", (req, res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/administrator.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/administrator/view-barbers", (req, res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("barbers", Barber.all());
      model.put("template", "templates/view-barbers.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/administrator/view-clients", (req, res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/view-clients.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/administrator/add-barber", (req, res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-barber.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/administrator/add-client", (req, res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("barbers", Barber.all());
      model.put("template", "templates/add-client.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/administrator/add-barber/success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("barber-name");
      Barber barber = new Barber(name);
      barber.save();
      model.put("barber", barber);
      model.put("template", "templates/add-barber-success.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/administrator/barber/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Barber barber = Barber.find(Integer.parseInt(request.params(":id")));
      model.put("barber", barber);
      model.put("template", "templates/admin-barber.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/administrator/barber/:id/add-client-success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("client-name");
      int barberId = Integer.parseInt(request.queryParams("barberId"));
      Barber barber = Barber.find(barberId);
      Client client = new Client(name, barberId);
      client.save();
      model.put("client", client);
      model.put("template", "templates/add-client-success.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });
  }
}
