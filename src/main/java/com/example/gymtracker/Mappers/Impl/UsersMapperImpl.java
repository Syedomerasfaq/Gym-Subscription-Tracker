package com.example.gymtracker.Mappers.Impl;

import com.example.gymtracker.Domain.Dto.UsersDto;
import com.example.gymtracker.Domain.entities.Users;
import com.example.gymtracker.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersMapperImpl implements Mapper<Users, UsersDto> {

    private ModelMapper modelMapper;

    public UsersMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersDto mapTo(Users users) {
        return modelMapper.map(users,UsersDto.class);
    }

    @Override
    public Users mapFrom(UsersDto usersDto) {
        return modelMapper.map(usersDto,Users.class);
    }
}
