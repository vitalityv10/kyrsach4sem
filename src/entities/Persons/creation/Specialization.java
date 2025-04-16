package entities.Persons.creation;

public enum Specialization {
    CARDIOLOGIST, NEUROLOGIST,
    DERMATOLOGIST, ORTHOPEDIST,
    OPHTHALMOLOGIST, OTORHINOLARYNGOLOGIST,
    PEDIATRICIAN, SURGEON,
    PSYCHIATRIST, ENDOCRINOLOGIST;
    public static void printSpecializations() {
        Specialization[] specializations = Specialization.values();
        for (int i = 0; i < specializations.length; i++) {
            System.out.println((i + 1) + ". " + specializations[i]);
        }
    }
}
