package com.connectify.connectify10.Service.ServiceImpl;



import java.util.List;
import java.util.Optional;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.connectify.connectify10.Repository.ContactRepository;
import com.connectify.connectify10.Service.ContactService;
import com.connectify.connectify10.entity.Contact;
import com.connectify.connectify10.entity.User;
import com.connectify.connectify10.helpers.ResourceNotFoundException;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private  ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        String contacId = UUID.randomUUID().toString();

        contact.setId(contacId);
        return contactRepository.save(contact);
       
    }

    @Override
    public Contact update(Contact contact) {
       var contactOld = contactRepository.findById(contact.getId()).orElseThrow(()-> new ResourceNotFoundException("contact Not found"));
       contactOld.setName(contact.getName());
       contactOld.setEmail(contact.getEmail());
       contactOld.setPhoneNumber(contact.getPhoneNumber());
       contactOld.setAddress(contact.getAddress());
       contactOld.setDescription(contact.getDescription());
       contactOld.setPicture(contact.getPicture());
       contactOld.setFavorite(contact.isFavorite());
       contactOld.setLinkdinLink(contact.getLinkdinLink());
       contactOld.setWebsiteLink(contact.getWebsiteLink());
       contactOld.setCloudninaryImagePublicId(contact.getCloudninaryImagePublicId());
       //contactOld.setLinks(contact.getLinks());
       

       return contactRepository.save(contactOld);
    
    }

    @Override
    public List<Contact> getAll() {
       
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not found"+id));
        
    }

    @Override
    public void delete(String id) {
        var contact = contactRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not found"+id));

        contactRepository.delete(contact);
    }


    @Override
    public List<Contact> getUserById(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(User user,int page,int size,String sortBy,String direction) {


       Sort sort = direction.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
       var pageable = PageRequest.of(page,size,sort);
       return contactRepository.findByUser(user,pageable);
    }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order,User user) {
    
       Sort sort =order.equals("desc")?Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
       var pageable = PageRequest.of(page, size,sort);
       return contactRepository.findByUserAndNameContaining(user,nameKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,User user) {
        
       Sort sort =order.equals("desc")?Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
       var pageable = PageRequest.of(page, size,sort);
       return contactRepository.findByUserAndEmailContaining(user,emailKeyword, pageable);
       
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
            String order,User user) {
      
                
       Sort sort =order.equals("desc")?Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
       var pageable = PageRequest.of(page, size,sort);
       return contactRepository.findByUserAndPhoneNumberContaining( user,phoneNumberKeyword,pageable);
    }

    @Override
    public Optional<Contact> findById(String id) {
        return contactRepository.findById(id);
    }
    @Override
    public long getTotalContactCount() {
        return contactRepository.count();
    }

    @Override
    public long getContactCountByUser(String userId) {
        return contactRepository.countByUserId(userId);
    }

  
   

  

   

}
