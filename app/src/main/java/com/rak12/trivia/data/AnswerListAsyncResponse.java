package com.rak12.trivia.data;

import com.rak12.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public interface  AnswerListAsyncResponse {
    void processfinished(ArrayList<Question> questionArrayList);
}
