package com.company.libraryManagment.service;

import com.company.libraryManagment.entity.Notification;
import com.company.libraryManagment.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void create(Notification notification){
        notificationRepository.save(notification);
    }
    public void saveById (Long id) {
        Notification notification = notificationRepository.findById(id).get();
        notificationRepository.save(notification);
    }
    public List<Notification> getAll(){
       return notificationRepository.findAll();
    }
    public void delete(long id){
        notificationRepository.deleteById(id);
    }
}
