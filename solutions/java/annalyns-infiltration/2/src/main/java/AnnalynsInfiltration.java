class AnnalynsInfiltration {
    public static boolean canFastAttack(boolean knightIsAwake) {
        // Can fast attack if the knight is sleeping
        return !knightIsAwake;
    }

    public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
        // Can be spied upon if at least character is awake
        return knightIsAwake || archerIsAwake || prisonerIsAwake;
    }

    public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
        // Can be signaled if the prisoner is awake and the archer is sleeping.
        return prisonerIsAwake && !archerIsAwake;
    }

    public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake, boolean petDogIsPresent) {
        // If Annalyn has her pet dog, she can rescue the prisoner if the archer is asleep.
        // If Annalyn does not have her pet dog, she can rescue the prisoner if the prisoner
        // is awake and both the knight and archer are sleeping.
        boolean rescueWithPetDog = petDogIsPresent && !archerIsAwake;
        boolean resuceWithoutPetDog = !petDogIsPresent && prisonerIsAwake && !knightIsAwake && !archerIsAwake;
        return rescueWithPetDog || resuceWithoutPetDog;
    }
}
