package com.example.coffe_brew_api.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.coffe_brew_api.dto.CoffeeBeanRequestDto;
import com.example.coffe_brew_api.dto.CoffeeBeanResponseDto;
import com.example.coffe_brew_api.model.BrewMethod;
import com.example.coffe_brew_api.model.CoffeeBean;
import com.example.coffe_brew_api.repository.CoffeeBeanRepository;

@ExtendWith(MockitoExtension.class)
public class CoffeeBeanServiceTest {
  
  @Mock
  private CoffeeBeanRepository repository;

  @InjectMocks
  private CoffeeBeanService coffeeBeanService;

  private CoffeeBeanRequestDto requestDto;
  private CoffeeBean savedBean;

  @BeforeEach
  void setUp() {
    requestDto = new CoffeeBeanRequestDto();
    requestDto.setName("Ethiopia");
    requestDto.setOrigin("Africa");
    requestDto.setFlavor("Floral");
    requestDto.setBrewMethod(BrewMethod.HAND_DRIP);

    savedBean = new CoffeeBean("Ethiopia", "Africa", "Floral", BrewMethod.HAND_DRIP);
    savedBean.setId(1L);
  }

  @Test
  @DisplayName("createCoffeeBean - 正常系")
  void createCoffeeBean_success() {

    when(repository.save(any(CoffeeBean.class))).thenReturn(savedBean);
    
    // モックの設定
    CoffeeBeanResponseDto result = coffeeBeanService.createCoffeeBean(requestDto);
    // 結果の検証
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getName()).isEqualTo("Ethiopia");
    assertThat(result.getOrigin()).isEqualTo("Africa");
    assertThat(result.getFlavor()).isEqualTo("Floral");
    assertThat(result.getBrewMethod()).isEqualTo("Hand Drip");

    //モックの呼び出し
    verify(repository, times(1)).save(any(CoffeeBean.class));
  }

  @Test
  @DisplayName("addBean - 正常系")
  void addBean_success() {
    when(repository.save(any(CoffeeBean.class))).thenReturn(savedBean);

    CoffeeBean result = coffeeBeanService.addBean(requestDto);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getName()).isEqualTo("Ethiopia");
    assertThat(result.getOrigin()).isEqualTo("Africa");
    assertThat(result.getFlavor()).isEqualTo("Floral");
    assertThat(result.getBrewMethod()).isEqualTo(BrewMethod.HAND_DRIP);

    verify(repository, times(1)).save(any(CoffeeBean.class));
  }

  @Test
  @DisplayName("addBean - nameがnullの場合はIllegalArgumentExceptionをスロー")
  void addBean_nullName_throwsException() {
    //nameがnullをDTOの作成
    CoffeeBeanRequestDto invalidDto = new CoffeeBeanRequestDto (
      null,
      "Africa",
      "Floral",
      BrewMethod.HAND_DRIP
    );

  assertThatThrownBy(() -> coffeeBeanService.addBean(invalidDto))
    .isInstanceOf(IllegalArgumentException.class)
    .hasMessageContaining("name must not be null");

    verify(repository, never()).save(any());
  }

  @Test
  @DisplayName("convertToDto - 正常系")
  void convertToDto_success() {
    CoffeeBean bean = new CoffeeBean("Ethiopia", "Africa", "Floral", BrewMethod.HAND_DRIP);
    bean.setId(1L);

    CoffeeBeanResponseDto dto = coffeeBeanService.convertToDto(bean);

    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo(1L);
    assertThat(dto.getName()).isEqualTo("Ethiopia");
    assertThat(dto.getOrigin()).isEqualTo("Africa");
    assertThat(dto.getFlavor()).isEqualTo("Floral");
    assertThat(dto.getBrewMethod()).isEqualTo("Hand Drip");
  }

  @Test
  @DisplayName("searchByKeyword - キーワードありの場合")
  void searchKeyword_withKeyword() {
    String keyword = "Ethiopia";

    List<CoffeeBean> mockBeans = List.of(savedBean);

    when(repository.searchByKeyword(keyword)).thenReturn(mockBeans);

    List<CoffeeBeanResponseDto> result = coffeeBeanService.searchByKeyword(keyword);

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getName()).isEqualTo("Ethiopia");

    verify(repository, times(1)).searchByKeyword(keyword);
    verify(repository, never()).findAll();
  }

  @Test
  @DisplayName("searchKeyword - キーワードなしの場合")
  void searchKeyword_withoutKeyword() {
    //検索キーワード空
    String keyword = "";

    //savedBeanを返す
    List<CoffeeBean> mockBeans = List.of(savedBean);

    //repositoryのfindAllが呼ばれたらmockBeansを返す
    when(repository.findAll()).thenReturn(mockBeans);

    //searchByKeywordメソッドを実行
    List<CoffeeBeanResponseDto> result = coffeeBeanService.searchByKeyword(keyword);

    //結果の検証：一件だけ返るごとに、名前が一致することを確認
    assertThat(result).hasSize(1);
    assertThat(result.get(0).getName()).isEqualTo("Ethiopia");
    
    //repositoryのfindAllが一回呼ばれたか検証
    verify(repository, times(1)).findAll();

    //repositoryのsearchByKeywordは呼ばれてないことを検証
    verify(repository, never()).searchByKeyword(anyString());
  }
}
