import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Navigator navigator = new NavigatorImpl();

        Route route1 = new Route("маршрут1", 3, true, Arrays.asList("J", "B", "C"));
        Route route2 = new Route("маршрут2", 2, false, Arrays.asList("B", "C", "D"));
        Route route3 = new Route("маршрут3", 7, false, Arrays.asList("J", "C", "L"));
        Route route7 = new Route("маршрут7", 3, true, Arrays.asList("J", "D", "L"));
        Route route8 = new Route("маршрут8", 4, true, Arrays.asList("Z", "X", "L"));
        Route route9 = new Route("маршрут9", 10, true, Arrays.asList("J", "L", "K", "B"));
        Route route10 = new Route("маршрут10", 4.7, true, Arrays.asList("L", "K"));
        Route route11 = new Route("маршрут11", 11, false, Arrays.asList("J", "K", "L", "M"));
        Route route12 = new Route("маршрут12", 12, false, Arrays.asList("J", "L", "M"));
        Route route4 = new Route("маршрут4", 12.3, true, Arrays.asList("C", "D", "K", "F"));
        Route route55 = new Route("маршрут55", 7, false, Arrays.asList("J", "C", "L"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route7);
        navigator.addRoute(route8);
        navigator.addRoute(route9);
        navigator.addRoute(route10);
        navigator.addRoute(route11);
        navigator.addRoute(route12);
        navigator.addRoute(route55);
        navigator.chooseRoute("маршрут9");
        navigator.chooseRoute("маршрут9");
        navigator.chooseRoute("маршрут9");
        navigator.chooseRoute("маршрут1");
        navigator.chooseRoute("маршрут1");
        navigator.chooseRoute("маршрут1");
        navigator.chooseRoute("маршрут1");
        navigator.chooseRoute("маршрут3");
        navigator.chooseRoute("маршрут4");
        navigator.chooseRoute("маршрут4");
        navigator.chooseRoute("маршрут4");
        navigator.chooseRoute("маршрут4");

        System.out.println("Размер навигатора: " + navigator.size());

        System.out.println("Наличие маршрута route3: " + navigator.contains(route3));

        Route getRoute = navigator.getRoute("маршрут8");
        System.out.println("Маршрут с идентификатором маршрут8: " + getRoute);

        Iterable<Route> matchingRoutes1 = navigator.searchRoutes("J", "L");
        System.out.println("Маршруты от J до L: ");
        for (Route route : matchingRoutes1) {
            System.out.println(route);
        }

        Iterable<Route> favoriteRoutes = navigator.getFavoriteRoutes("L");
        System.out.println("Избранные маршруты до точки L:");
        for (Route route : favoriteRoutes) {
            System.out.println(route);
        }

        Iterable<Route> top3Routes = navigator.getTop3Routes();
        System.out.println("Топ 3 маршрута: ");
        for (Route route : top3Routes) {
            System.out.println(route);
        }
        System.out.println("удаление первого маршрута");
        navigator.removeRoute("маршрут1");
        System.out.println("Размер навигатора: " + navigator.size());

        System.out.println(navigator.getRoute("маршрут3"));
    }
}
