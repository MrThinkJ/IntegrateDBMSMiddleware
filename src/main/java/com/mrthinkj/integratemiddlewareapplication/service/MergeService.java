package com.mrthinkj.integratemiddlewareapplication.service;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.payload.UpdateInfo;

import java.util.List;

public interface MergeService {
    List<MergePerson> mergeAllPerson();
    String deleteFromTwoDBMS(String firstName, String lastName);
    MergePerson updateFromTwoDBMS(Integer typeId, boolean isUpdated, MergePerson mergePerson);
    UpdateInfo getUpdateInfo(String firstName, String lastName);
}
