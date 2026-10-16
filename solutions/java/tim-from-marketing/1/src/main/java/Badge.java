class Badge {
    public String print(Integer id, String name, String department) {

        String badge = "";
        if (department == null) {
            department = "OWNER";
        }
        if (id == null) {
            badge = name.concat(" - ").concat(department.toUpperCase());
        } else {
            badge = "[".concat(id.toString()).concat("] - ").concat(name).concat(" - ").concat(department.toUpperCase());
        }
        return badge;
    }
}
