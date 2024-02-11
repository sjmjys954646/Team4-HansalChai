package com.hansalchai.haul.common.utils;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hansalchai.haul.common.utils.KaKaoMap.KakaoMap;

@SpringBootTest
public class KaKaoMapTest {
	@Autowired
	private KakaoMap kakaoMap;
	@Test
	@DisplayName("unittest : 카카오맵 API가 정상적으로 동작한다.")
	void carPathFindTest() {
		//given
		MapUtils.Location src = new MapUtils.Location(BigDecimal.valueOf(127.11015314141542),BigDecimal.valueOf(37.39472714688412));
		MapUtils.Location dst = new MapUtils.Location(BigDecimal.valueOf(127.10824367964793),BigDecimal.valueOf(37.401937080111644));
		int val1 = 1033;
		int val2 = 254;
		MapUtils.DistanceDurationInfo expected = new MapUtils.DistanceDurationInfo(val1, val2);
		//when
		MapUtils.DistanceDurationInfo actual = kakaoMap.carPathFind(src, dst);

		//Then
		Assertions.assertEquals(expected.getDistance(), actual.getDistance());
		//duration은 계속 바뀜.
	}
}
