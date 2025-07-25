package me.rerere.rikkahub.ui.components.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.css
import me.rerere.rikkahub.ui.components.chat.TextAvatar
import me.rerere.rikkahub.utils.toCssHex

@Composable
fun AIIcon(
  path: String,
  name: String,
  modifier: Modifier = Modifier,
) {
  val contentColor = LocalContentColor.current
  val context = LocalContext.current
  val model = remember(path, contentColor, context) {
    ImageRequest.Builder(context)
      .data("file:///android_asset/icons/$path")
      .css(
        """
                svg {
                  fill: ${contentColor.toCssHex()};
                }
            """.trimIndent()
      )
      .build()
  }
  AsyncImage(
    model = model,
    contentDescription = name,
    modifier = modifier
        .clip(CircleShape)
        .size(24.dp),
  )
}

@Composable
fun AutoAIIcon(
  name: String,
  modifier: Modifier = Modifier,
) {
  val path = remember(name) { computeAIIconByName(name) } ?: run {
    TextAvatar(name, modifier)
    return
  }
  AIIcon(
    path = path,
    name = name,
    modifier = modifier,
  )
}

@Preview
@Composable
private fun PreviewAutoAIIcon() {
  Column {
    AutoAIIcon("测试")
  }
}

// https://lobehub.com/zh/icons
private fun computeAIIconByName(name: String): String? {
  // 检查缓存
  ICON_CACHE[name]?.let { return it }

  val lowerName = name.lowercase()
  val result = when {
    PATTERN_OPENAI.containsMatchIn(lowerName) -> "openai.svg"
    PATTERN_GEMINI.containsMatchIn(lowerName) -> "gemini-color.svg"
    PATTERN_GOOGLE.containsMatchIn(lowerName) -> "google-color.svg"
    PATTERN_ANTHROPIC.containsMatchIn(lowerName) -> "anthropic.svg"
    PATTERN_CLAUDE.containsMatchIn(lowerName) -> "claude-color.svg"
    PATTERN_DEEPSEEK.containsMatchIn(lowerName) -> "deepseek-color.svg"
    PATTERN_GROK.containsMatchIn(lowerName) -> "grok.svg"
    PATTERN_QWEN.containsMatchIn(lowerName) -> "qwen-color.svg"
    PATTERN_DOUBAO.containsMatchIn(lowerName) -> "doubao-color.svg"
    PATTERN_OPENROUTER.containsMatchIn(lowerName) -> "openrouter.svg"
    PATTERN_ZHIPU.containsMatchIn(lowerName) -> "zhipu-color.svg"
    PATTERN_MISTRAL.containsMatchIn(lowerName) -> "mistral-color.svg"
    PATTERN_META.containsMatchIn(lowerName) -> "meta-color.svg"
    PATTERN_HUNYUAN.containsMatchIn(lowerName) -> "hunyuan-color.svg"
    PATTERN_GEMMA.containsMatchIn(lowerName) -> "gemma-color.svg"
    PATTERN_PERPLEXITY.containsMatchIn(lowerName) -> "perplexity-color.svg"
    PATTERN_ALIYUN.containsMatchIn(lowerName) -> "alibabacloud-color.svg"
    PATTERN_BYTEDANCE.containsMatchIn(lowerName) -> "bytedance-color.svg"
    PATTERN_SILLICON_CLOUD.containsMatchIn(lowerName) -> "siliconcloud-color.svg"
    PATTERN_AIHUBMIX.containsMatchIn(lowerName) -> "aihubmix-color.svg"
    PATTERN_OLLAMA.containsMatchIn(lowerName) -> "ollama.svg"
    PATTERN_GITHUB.containsMatchIn(lowerName) -> "github.svg"
    PATTERN_CLOUDFLARE.containsMatchIn(lowerName) -> "cloudflare-color.svg"
    PATTERN_MINIMAX.containsMatchIn(lowerName) -> "minimax-color.svg"
    PATTERN_XAI.containsMatchIn(lowerName) -> "xai.svg"
    PATTERN_JUHENEXT.containsMatchIn(lowerName) -> "juhenext.png"
    else -> null
  }

  // 保存到缓存
  result?.let { ICON_CACHE[name] = it }

  return result
}

// 静态缓存和正则模式
private val ICON_CACHE = mutableMapOf<String, String>()
private val PATTERN_OPENAI = Regex("(gpt|openai|o\\d)")
private val PATTERN_GEMINI = Regex("(gemini)")
private val PATTERN_GOOGLE = Regex("google")
private val PATTERN_ANTHROPIC = Regex("anthropic")
private val PATTERN_CLAUDE = Regex("claude")
private val PATTERN_DEEPSEEK = Regex("deepseek")
private val PATTERN_GROK = Regex("grok")
private val PATTERN_QWEN = Regex("qwen|qwq|qvq")
private val PATTERN_DOUBAO = Regex("doubao")
private val PATTERN_OPENROUTER = Regex("openrouter")
private val PATTERN_ZHIPU = Regex("zhipu|智谱|glm")
private val PATTERN_MISTRAL = Regex("mistral")
private val PATTERN_META = Regex("meta|(?<!o)llama")
private val PATTERN_HUNYUAN = Regex("hunyuan|tencent")
private val PATTERN_GEMMA = Regex("gemma")
private val PATTERN_PERPLEXITY = Regex("perplexity")
private val PATTERN_BYTEDANCE = Regex("bytedance|火山")
private val PATTERN_ALIYUN = Regex("aliyun|阿里云|百炼")
private val PATTERN_SILLICON_CLOUD = Regex("silicon|硅基")
private val PATTERN_AIHUBMIX = Regex("aihubmix")
private val PATTERN_OLLAMA = Regex("ollama")
private val PATTERN_GITHUB = Regex("github")
private val PATTERN_CLOUDFLARE = Regex("cloudflare")
private val PATTERN_MINIMAX = Regex("minimax")
private val PATTERN_XAI = Regex("xai")
private val PATTERN_JUHENEXT = Regex("juhenext")
