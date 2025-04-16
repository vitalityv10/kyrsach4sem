package strategies;

import entities.Persons.creation.Specialization;

public interface SpecializationRemovable<T> extends Removable<T> {
    void removeBySpecialization(Specialization specialization);
}
