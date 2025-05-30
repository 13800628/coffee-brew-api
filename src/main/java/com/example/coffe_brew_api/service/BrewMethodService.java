package com.example.coffe_brew_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.coffe_brew_api.dto.BrewMethodResponse;

@Service
public class BrewMethodService {
    
  public List<BrewMethodResponse> getAllMethods() {
    return List.of(
      new BrewMethodResponse(
        "ハンドドリップコーヒー",
        "丁寧にお湯を注ぐことで、香りと味の調整がしやすい抽出方法です。",
        List.of(
          "1. ドリッパーとペーパーをセット",
          "2. 中挽き豆を使用",
          "3. 蒸らしの為少量お湯を注ぐ(30秒)",
          "4. ゆっくりと残りのお湯を注ぐ"
        ),
        40
      ),
      new BrewMethodResponse(
        "フレンチプレス",
        "簡単にコクのあるコーヒーが楽しめます",
        List.of(
          "1. 粗挽き豆を入れる",
          "2. お湯を注ぐ",
          "3. 4分間待つ",
          "4. プレスして注ぐ"
        ),
        240
      ),
      new BrewMethodResponse(
        "コールドブリュー",
        "低温でゆっくりと抽出するため、酸味が少なくまろやかな味わいに。",
        List.of(
          "1. 粗挽き豆と水を容器に入れる",
          "2. 冷蔵庫で12時間以上抽出",
          "3. フィルターで濾して完成"
        )
      )
     
    );
  }
}
