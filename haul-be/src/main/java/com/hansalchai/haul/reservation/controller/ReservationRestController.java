package com.hansalchai.haul.reservation.controller;

import static com.hansalchai.haul.common.auth.jwt.JwtProvider.*;
import static com.hansalchai.haul.common.utils.ApiResponse.*;
import static com.hansalchai.haul.reservation.dto.ReservationRequest.*;
import static com.hansalchai.haul.reservation.dto.ReservationResponse.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansalchai.haul.common.auth.constants.Role;
import com.hansalchai.haul.common.auth.dto.AuthenticatedUser;
import com.hansalchai.haul.common.utils.ApiResponse;
import com.hansalchai.haul.common.utils.SuccessCode;
import com.hansalchai.haul.reservation.dto.ReservationRequest;
import com.hansalchai.haul.reservation.dto.ReservationResponse;
import com.hansalchai.haul.reservation.service.ReservationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReservationRestController {
	private final ReservationService reservationService;

	@PostMapping("/reservations")
	public ResponseEntity<ApiResponse<ReservationRecommendationDTO>> postCustomerReservation(
		@Valid @RequestBody CreateReservationDTO reservationDTO,
		HttpServletRequest request
	) {
		AuthenticatedUser auth = (AuthenticatedUser)request.getAttribute(AUTHENTICATE_USER);
		ReservationRecommendationDTO response = reservationService.createReservation(reservationDTO, auth.getUserId());
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@PostMapping("/reservations/guest")
	public ResponseEntity<ApiResponse<ReservationRecommendationDTO>> postGuestReservation(
		@Valid @RequestBody CreateReservationGuestDTO reservationDTO
	) {
		ReservationRecommendationDTO response = reservationService.createGuestReservation(reservationDTO);
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping("/reservations")
	public ResponseEntity<ApiResponse<List<ReservationDTO>>> getCustomerReservation(HttpServletRequest request){
		AuthenticatedUser auth = (AuthenticatedUser)request.getAttribute(AUTHENTICATE_USER);
		List<ReservationDTO> response = reservationService.getReservation(auth.getUserId());
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

}
