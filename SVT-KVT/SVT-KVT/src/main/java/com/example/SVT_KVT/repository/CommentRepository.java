package com.example.SVT_KVT.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SVT_KVT.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {}
