package com.example.ScoreUniversity.mapper;

import com.example.ScoreUniversity.dto.ScoreDTO;
import com.example.ScoreUniversity.entity.Score;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScoreMapper {
    ScoreMapper MAPPER = Mappers.getMapper(ScoreMapper.class);
    Score scoreDTOToScore(ScoreDTO scoreDTO);
    ScoreDTO scoreToScoreDTO(Score score);

}
