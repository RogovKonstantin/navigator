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
            if (locationPoints.get(0).equals(startPoint) && locationPoints.get(locationPoints.size()-1).equals(endPoint)) {
                if (route.isFavorite()) {
                    favoriteRoutes.add(route);
                } else {
                    matchingRoutes.add(route);
                }
            }
        }

        favoriteRoutes.sort(Comparator.comparingDouble(Route::getDistance)
                .thenComparingInt(Route::getPopularity).reversed());

        matchingRoutes.sort(Comparator.comparingDouble(Route::getDistance)
                .thenComparingInt(Route::getPopularity).reversed());

        List<Route> result = new ArrayList<>();
        result.addAll(favoriteRoutes);
        result.addAll(matchingRoutes);

        // Remove duplicates
        result = result.stream().distinct().collect(Collectors.toList());

        return result;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        List<Route> matchingRoutes = new ArrayList<>();
        for (String id : routes.keySet()) {
            Route route = routes.get(id);
            List<String> locationPoints = route.getLocationPoints();
            if (route.isFavorite() && !locationPoints.get(0).equals(destinationPoint) && locationPoints.contains(destinationPoint)) {
                matchingRoutes.add(route);
            }
        }

        Collections.sort(matchingRoutes, Comparator.comparingDouble(Route::getDistance).reversed()
                .thenComparingInt(Route::getPopularity).reversed());

        // Remove duplicates
        matchingRoutes = matchingRoutes.stream().distinct().collect(Collectors.toList());

        return matchingRoutes;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        List<Route> topRoutesList = new ArrayList<>(routes.values());

        Collections.sort(topRoutesList, (r1, r2) -> {
            // First, sort the list by popularity
            int popularityComparison = Integer.compare(r2.getPopularity(), r1.getPopularity());
            if (popularityComparison != 0) {
                return popularityComparison;
            }

            // Then, sort the list by distance (in ascending order)
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            if (distanceComparison != 0) {
                return distanceComparison;
            }

            // Finally, sort the list by the number of location points (in ascending order)
            return Integer.compare(r1.getLocationPoints().size(), r2.getLocationPoints().size());
        });

        if (topRoutesList.size() > 3) {
            topRoutesList = topRoutesList.subList(0, 3);
        }

        // Remove duplicates
        topRoutesList = topRoutesList.stream().distinct().collect(Collectors.toList());

        return topRoutesList;
    }
}