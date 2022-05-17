<template>
  <h1>살모넬라</h1>

  <h3>주의 식재료</h3>
  <img src='@/assets/egg.png' width="50px" height="50px">
  <img src='@/assets/milk.png' width="50px" height="50px">
  <img src='@/assets/meat.png' width="50px" height="50px">
  <img src='@/assets/kimbap.png' width="50px" height="50px">
  <hr />

  <div class="detail-content">    
    <h3>전파경로</h3>
    <ul>
      <li>감염 동물에서 유래된 음식, 균에 오염된 분변에 노출된 음식 등을 먹어서 감염됨</li>
      <li>날달걀, 덜 익힌 달걀 및 달걀 가공품, 생우유와 생우유 가공품, 오염된 물, 오염된 육류/육류 가공품, 가금류/가금류 가공품이 감염 원인 식품임</li>
      <li>살모넬라균에 감염된 동물이나 감염된 동물 주변 환경에 접촉하여 감염 가능</li>
      <li>설사 증상이 있을 경우 사람 간 분변－구강 경로로 감염 가능</li>
      <li>병원체는 우유 등 다양한 식품에서 증식하여 감염력 높은 용량에 이를 수 있음</li>
      <li>음식 준비 시 상온에 재료를 방치하거나, 음식 손질 시 교차 오염이 가장 큰 위험 요인임</li>
    </ul>

    <h3>잠복기</h3>
    <ul>
      <li>6∼72시간(대부분 12∼36시간)</li>
      <li>긴 잠복기는 16일까지 보고되고 있으나, 낮은 균 용량을 섭취 시 긴 잠복기는 드묾</li>
    </ul>

    <h3>임상증상</h3>
    <ul>
      <li>급성 설사(혈성 설사 가능)</li>
      <li>경련성 복통</li>
      <li>발열</li>
    </ul>

    <h3>예방법</h3>
    <ul>
      <li>신선한 식품 구입(난각일자 확인, 냉장보관전 신선식품 구입)</li>
      <li>충분히 익혀 섭취(75℃,1분)</li>
      <li>조리식품은 가급적 빠른 시간 내 섭취</li>
      <li>칼, 도마를 통한 교차오염 주의</li>
    </ul>



  </div>
</template>

<script>

export default {
  name: "salmonellaDetail",

}
</script>

<style scoped>
.detail-content {
  text-align: left;
}
h3 {
  text-align: left;
}
img{
  margin: 5px;
  margin-left: 10px;
  margin-right: 10px;
}
</style>
