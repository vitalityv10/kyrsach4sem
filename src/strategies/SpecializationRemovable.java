package strategies;

import entities.Persons.Specialization;

public interface SpecializationRemovable<T> extends Removable<T> {
    void removeBySpecialization(Specialization specialization);
}
