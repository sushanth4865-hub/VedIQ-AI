package com.sscai.vediq.academic.repository;

import com.sscai.vediq.academic.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}