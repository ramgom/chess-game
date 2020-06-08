package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MoveValidatorProvider {

    private final Map<PieceType, MoveValidator> validators;

    public MoveValidatorProvider(List<MoveValidator> moveValidators) {
        this.validators = moveValidators.stream()
                .collect(Collectors.toMap(MoveValidator::getType, Function.identity()));
    }

    public MoveValidator moveValidator(Piece piece) {
        return validators.get(piece.type());
    }
}
