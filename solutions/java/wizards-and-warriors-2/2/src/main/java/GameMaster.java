public class GameMaster {

    public String describe(Character character) {
        return String.format("You're a level %s %s with %s hit points."
                , character.getLevel()
                , character.getCharacterClass()
                , character.getHitPoints());
    }

    public String describe(Destination destination) {
        return String.format("You've arrived at %s, which has %s inhabitants."
                , destination.getName()
                , destination.getInhabitants());
    }

    public String describe(TravelMethod travelMethod) {
        var tmDescription = switch (travelMethod) {
            case TravelMethod.HORSEBACK -> TravelMethod.HORSEBACK.getDescription();
            case TravelMethod.WALKING ->  TravelMethod.WALKING.getDescription();
        };
        return String.format("You're traveling to your destination %s."
                , tmDescription);
    }

    public String describe(Character c, Destination d, TravelMethod tm) {
        return String.format("%s %s %s"
                , describe(c)
                , describe(tm)
                , describe(d));
    }
    
    public String describe(Character c, Destination d) {
        return String.format("%s %s %s"
                , describe(c)
                , describe(TravelMethod.WALKING)
                , describe(d));
    }
}
