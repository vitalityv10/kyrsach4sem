package entities.Persons.creation;
import java.util.List;
import java.util.Map;
public enum Diagnosis {;
    public static final Map<Specialization, List<String>> DIAGNOSES = Map.of(
            Specialization.CARDIOLOGIST, List.of("Гіпертонія", "Аритмія"),
            Specialization.NEUROLOGIST, List.of("Мігрень", "Інсульт"),
            Specialization.DERMATOLOGIST, List.of("Акне", "Екзема"),
            Specialization.ORTHOPEDIST, List.of("Перелом", "Артрит"),
            Specialization.OPHTHALMOLOGIST, List.of("Катаракта", "Короткозорість"),
            Specialization.OTORHINOLARYNGOLOGIST, List.of("Ангіна", "Отит"),
            Specialization.PEDIATRICIAN, List.of("Кір", "ГРВІ"),
            Specialization.SURGEON, List.of("Апендицит", "Грижа"),
            Specialization.PSYCHIATRIST, List.of("Депресія", "Тривожність"),
            Specialization.ENDOCRINOLOGIST, List.of("Діабет", "Гіпотиреоз")
    );
    public static final Map<String, String> TREATMENTS = Map.ofEntries(
            Map.entry("Гіпертонія", "Антигіпертензивні препарати"),
            Map.entry("Аритмія", "Бета-блокатори"), Map.entry("Мігрень", "Знеболювальні"), Map.entry("Інсульт", "Реабілітація"),
            Map.entry("Акне", "Антибіотики"), Map.entry("Екзема", "Мазі з кортикостероїдами"), Map.entry("Перелом", "Гіпс"),
            Map.entry("Артрит", "Протизапальні засоби"), Map.entry("Катаракта", "Операція"), Map.entry("Короткозорість", "Окуляри"),
            Map.entry("Ангіна", "Антибіотики"), Map.entry("Отит", "Краплі для вух"), Map.entry("Кір", "Вітамінна терапія"),
            Map.entry("ГРВІ", "Парацетамол"), Map.entry("Апендицит", "Хірургічне втручання"), Map.entry("Грижа", "Операція"),
            Map.entry("Депресія", "Антидепресанти"), Map.entry("Тривожність", "Терапія"),
            Map.entry("Діабет", "Інсулін"), Map.entry("Гіпотиреоз", "Гормональна терапія"));
}
