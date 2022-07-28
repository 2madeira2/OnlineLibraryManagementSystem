package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.TagDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(TagDTO tagDTO);
}
