import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NavigatorImpl implements Navigator {
    private CustomMap<String, Route> routes;

    public NavigatorImpl() {
        this.routes = new CustomMap<>();
    }

    @Override
    public void addRoute(Route route) {
        routes.put(route.getId(), route);
    }

    @Override
    public boolean contains(Route route) {
        return routes.containsKey(route.getId());
    }

    @Override
    public Route getRoute(String id) {
        return routes.get(id);
    }

    @Override
    public void removeRoute(String id) {
        routes.remove(id);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public void chooseRoute(String id) {
        Route route = routes.get(id);
        if (route != null) {
            route.setPopularity(route.getPopularity() + 1);
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        List<Route> matchingRoutes = new ArrayList<>();
        List<Route> favoriteRoutes = new ArrayList<>();

        for (String routeId : this.routes.keySet()) {
            Route route = this.routes.get(routeId);
            List<String> locationPoints = route.getLocationPoints();
            //if (locationPoints.get(0).equals(startPoint) && !locationPoints.get(0).equals(endPoint) && locationPoints.contains(endPoint))
            if (locationPoints.getFirst().equals(startPoint) && locationPoints.getLast().equals(endPoint)) {
                if (route.isFavorite()) {
                    favoriteRoutes.add(route);
                } else {
                    matchingRoutes.add(route);
                }
            }
        }
//        favoriteRoutes.sort(Comparator.comparingInt((Route r) -> r.getLocationPoints().size())
//                .thenComparingInt(Route::getPopularity).reversed());
//
//        matchingRoutes.sort(Comparator.comparingInt((Route r) -> r.getLocationPoints().size())
//                .thenComparingInt(Route::getPopularity).reversed());

        favoriteRoutes.sort(Comparator.comparingDouble(Route::getDistance)
                .thenComparingInt(Route::getPopularity).reversed());

        matchingRoutes.sort(Comparator.comparingDouble(Route::getDistance)
                .thenComparingInt(Route::getPopularity).reversed());

        List<Route> routesResultList = new ArrayList<>();
        routesResultList.addAll(favoriteRoutes);
        routesResultList.addAll(matchingRoutes);


        routesResultList = routesResultList.stream().distinct().collect(Collectors.toList());

        return routesResultList;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        List<Route> matchingRoutes = new ArrayList<>();
        for (String id : routes.keySet()) {
            Route route = routes.get(id);
            List<String> locationPoints = route.getLocationPoints();
            if (route.isFavorite() && !locationPoints.getFirst().equals(destinationPoint) && locationPoints.contains(destinationPoint)) {
                matchingRoutes.add(route);
            }
        }

        matchingRoutes.sort(Comparator.comparingDouble(Route::getDistance).reversed().thenComparingInt(Route::getPopularity).reversed());
        matchingRoutes = matchingRoutes.stream().distinct().collect(Collectors.toList());

        return matchingRoutes;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        List<Route> topRoutesList = new ArrayList<>(routes.values());
        return topRoutesList.stream()
                .sorted(Comparator.comparing(Route::getPopularity).reversed().thenComparing(Route::getDistance).thenComparing(route -> route.getLocationPoints().size()))
                .limit(3).distinct().collect(Collectors.toList());
    }
}