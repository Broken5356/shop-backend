package com.broken.shopbackend.service.impl;

import com.broken.shopbackend.domain.Navigation;
import com.broken.shopbackend.mapper.NavigationMapper;
import com.broken.shopbackend.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService {
    @Autowired
    private NavigationMapper navigationMapper;

    @Override
    public List<Navigation> getNavigationList() {
        return navigationMapper.selectList(null);
    }
}
